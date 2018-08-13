package com.cl.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Autowired
    private  SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() {

        System.out.println(successKilledDao.insertSuccessKilled(1001L,2));
    }

    @Test
    public void queryByIdWithSeckill() {

        System.out.println(successKilledDao.queryByIdWithSeckill(1003L,2));
    }
}