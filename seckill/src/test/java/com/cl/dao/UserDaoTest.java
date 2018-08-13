package com.cl.dao;

import com.cl.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class UserDaoTest {

    @Autowired
    private  UserDao userDao;
    @Test
    public void findByUserName() {

        String username="爱吃鱼的猫";
        System.out.println(userDao.findByUserName(username));
    }

    @Test
    public  void findAll(){

        System.out.println(userDao.findAll());
    }
}