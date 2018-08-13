package com.cl.service;

import com.cl.dao.UserDao;
import com.cl.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService  {

    @Autowired
    private UserDao userDao;
    public User findByUserName(String username){

        return userDao.findByUserName(username);

    }

}
