package com.cl.web;

import com.cl.dto.Exposer;
import com.cl.dto.SeckillExecution;
import com.cl.dto.SeckillResult;
import com.cl.emnum.SeckillStateEnum;
import com.cl.entity.Seckill;
import com.cl.exception.RepeatKillException;
import com.cl.exception.SeckillColseException;
import com.cl.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public String list(Model model){
        List<Seckill> list= seckillService.getSeckillList();

        model.addAttribute("seckills",list);

        return  "list";
    }

    /**
     * 获取单个秒杀商品
     * @param seckillId
     * @param model
     * @return
     */
    @RequestMapping(value = "/{userId}/{seckillId}/detail",method = RequestMethod.GET)
    public  String detail(@PathVariable("seckillId") Long seckillId,@PathVariable("userId") Integer userId, Model model){

        if (seckillId==null ){

            return "redirect:/seckill/list";
        }
        model.addAttribute("seckill",seckillService.getSeckillById(seckillId));
        model.addAttribute("userId",userId);

        return  "detail";

    }

    /**
     * 暴露秒杀接口
     * @param seckillId
     * @return
     */
    @RequestMapping(value="/{seckillId}/exposer",method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId){

        Exposer exposer;
        SeckillResult<Exposer> seckillResult;
   try {
       exposer=seckillService.exportSeckillUrl(seckillId);
       seckillResult=new SeckillResult<Exposer>(true,exposer);
   }catch (Exception e){
       e.printStackTrace();
       seckillResult=new SeckillResult<Exposer>(false,e.getMessage());
   }
   return  seckillResult;
    }


    /**
     * 秒杀方法
     * @param seckillId
     * @param md5
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{userId}/{seckillId}/{md5}/execution",method = RequestMethod.POST,
    produces = {"application/json;charset=UTF-8"})
    public  SeckillResult<SeckillExecution> execution(@PathVariable("seckillId") Long seckillId,@PathVariable("md5")String md5,
                                                      @PathVariable("userId")  Integer userId){

        if(userId==0){
            return  new SeckillResult<SeckillExecution>(false,"用户未登陆");

        }
        SeckillExecution seckillExecution; //秒杀执行结果
        SeckillResult<SeckillExecution> seckillResult; //秒杀结果
        try{
            seckillExecution=seckillService.excuteSeckill(seckillId,userId,md5);
            seckillResult=new SeckillResult<SeckillExecution>(true,seckillExecution);
        }catch (RepeatKillException e){
            e.printStackTrace();
            SeckillExecution execution=new SeckillExecution(seckillId, SeckillStateEnum.REPEAT_KILL);
            seckillResult=new SeckillResult<SeckillExecution>(false,execution);
        }catch (SeckillColseException e){
            e.printStackTrace();
            SeckillExecution execution=new SeckillExecution(seckillId,SeckillStateEnum.COLSE);
            seckillResult=new SeckillResult<SeckillExecution>(false,execution);
        }
        catch (Exception e){
            e.printStackTrace();
            SeckillExecution execution=new SeckillExecution(seckillId,SeckillStateEnum.INNER_ERROR);
            seckillResult=new SeckillResult<SeckillExecution>(false,execution);

        }
       return  seckillResult;

    }
    //获取服务器时间
    @ResponseBody
    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    public  SeckillResult<Long>time(){

          return  new SeckillResult<Long>(true,new Date().getTime());
    }
}
