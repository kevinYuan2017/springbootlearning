package com.kevin.springboot.learning.chapter6.transaction.service.impl;

import com.kevin.springboot.learning.chapter6.transaction.dao.UserDao;
import com.kevin.springboot.learning.chapter6.transaction.pojo.User;
import com.kevin.springboot.learning.chapter6.transaction.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1)
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1)
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }
}
