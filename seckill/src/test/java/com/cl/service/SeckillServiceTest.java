package com.cl.service;

import com.cl.dto.Exposer;
import com.cl.dto.SeckillExecution;
import com.cl.entity.Seckill;
import com.cl.entity.SuccessSeckilled;
import com.cl.exception.SeckillException;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    @Autowired
    private SeckillService seckillService;

    private  static  final Logger logger=LoggerFactory.getLogger(SeckillServiceTest.class);
    @Test
    public void getSeckillList() {

        logger.info("日志打印");
        List<Seckill>list=seckillService.getSeckillList();
        logger.info("list={}",list);

    }

    @Test
    public void getSeckillById() {

        Seckill seckill=seckillService.getSeckillById(1001L);

        System.out.println(seckill);
    }

    @Test
    public void exportSeckillUrl() {

        Exposer exposer=seckillService.exportSeckillUrl(1006L);
        System.out.println(exposer);
    }

    @Test
    public void excuteSeckill() {

        SeckillExecution seckillExecution=seckillService.excuteSeckill(1002L,1,"82443bc7ed9ae7cabcf0ea7ffc7a5d53");


    }
}