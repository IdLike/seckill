package com.cl.service;

import com.cl.dao.SeckillDao;
import com.cl.dao.SuccessKilledDao;
import com.cl.dao.cache.RedisDao;
import com.cl.dto.Exposer;
import com.cl.dto.SeckillExecution;
import com.cl.emnum.SeckillStateEnum;
import com.cl.entity.Seckill;
import com.cl.entity.SuccessSeckilled;
import com.cl.exception.RepeatKillException;
import com.cl.exception.SeckillColseException;
import com.cl.exception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
@Service
public class SeckillServiceImpl implements  SeckillService {

    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessKilledDao successKilledDao;
    @Autowired
    private RedisDao redisDao;

    private Logger logger=LoggerFactory.getLogger(this.getClass());
    //MD5盐值字符串，用于混淆MD5
    private  final  String slat="22321!*rtredd456dgfedfw%^%&^*(*@(HEJSDDNFF";
    private  String  getMD5(Long seckillId){
        String base=seckillId+"/"+slat;
        String md5=DigestUtils.md5DigestAsHex(base.getBytes());
        return  md5;
    }

    public List<Seckill> getSeckillList() {
        return seckillDao.findAll(0,10);
    }

    public Seckill getSeckillById(Long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    /**
     * 暴露秒杀接口
     * @param seckillId
     * @return
     */
    public Exposer exportSeckillUrl(Long seckillId) {

        //缓存优化
        Seckill seckill=redisDao.getSeckill(seckillId);
        if(seckill==null){
            seckill=seckillDao.queryById(seckillId);
            //没有此商品参与秒杀
            if(seckill==null){
                return  new Exposer(false,seckillId);
            }else{
                //存入redis
                redisDao.putSeckill(seckill);
            }
        }

        //秒杀不在时间范围内
        Date start= seckill.getStartTime();
        Date end=seckill.getEndTime();
        Date now=new Date();
        if(now.getTime()<start.getTime() || now.getTime()>end.getTime()){
           return  new Exposer(false,seckillId,now.getTime(),start.getTime(),end.getTime());
        }

        String md5=getMD5(seckillId);

        return new Exposer(true,md5,seckillId);
    }

    /**
     *
     *保证事务方法的执行时间尽可能短，不要穿插其它网络操作可以剥离到事务外面
     */
    @Transactional
    public SeckillExecution excuteSeckill(Long seckillId, Integer userId, String md5) throws SeckillException, SeckillColseException, RepeatKillException {
      if(md5==null && !md5.equals(getMD5(seckillId))){
          throw  new SeckillException("seckill data rewirte");
      }
      //执行秒杀逻辑：减库存，记录购买行为

        Date now=new Date();
        int upadateCount=seckillDao.reduceNumber(seckillId,now);
        try {
            if(upadateCount<=0){
                //没有得到更新记录，秒杀结束
                throw  new SeckillColseException("秒杀结束");
            }else{
                //记录购买行为
                int insertCount=successKilledDao.insertSuccessKilled(seckillId,userId);

                    if(insertCount<=0){
                        //重复秒杀
                        throw  new RepeatKillException("重复秒杀");
                    }else {
                       //秒杀成功
                        SuccessSeckilled successSeckilled=successKilledDao.queryByIdWithSeckill(seckillId,userId);

                        return  new SeckillExecution(seckillId,SeckillStateEnum.SUCCESSS,successSeckilled);
                    }
            }
        }catch (SeckillColseException e){
            throw  e;
        }catch (RepeatKillException e){
            throw  e;
        } catch (Exception e){
            logger.error(e.getMessage(),e);
            throw  new SeckillException("seckill exception:"+e.getMessage());
        }
    }

}
