package com.cl.dao.cache;

import com.cl.dao.SeckillDao;
import com.cl.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {

    private  Long seckillId=1006L;
    @Autowired
    private  RedisDao redisDao;
    @Autowired
    private SeckillDao seckillDao;

    @Test
    public void testSeckill() {
        Seckill seckill=redisDao.getSeckill(seckillId);

        if(seckill==null){
            seckill=seckillDao.queryById(seckillId);
            if(seckill!=null){
                String result=redisDao.putSeckill(seckill);
                System.out.println(result);

                seckill=redisDao.getSeckill(seckillId);

                System.out.println(seckill);
            }
        }

    }


}