package com.cl.dao;

import com.cl.entity.User;

import java.util.List;


public interface UserDao {

    public User findByUserName(String username);

    public List<User> findAll();
}
