package com.cl.dao;

import com.cl.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
    @Autowired
    private  SeckillDao seckillDao;
    @Test
    public void reduceNumber() {

        System.out.println( seckillDao.reduceNumber(1000L,new Date()));

    }

    @Test
    public void queryById() {
        long id=1000;
        Seckill seckill=seckillDao.queryById(id);
        System.out.println(seckill.toString());
    }

    @Test
    public void findAll() {

        List<Seckill> list= seckillDao.findAll(0,5);

        System.out.println(list);
    }
}