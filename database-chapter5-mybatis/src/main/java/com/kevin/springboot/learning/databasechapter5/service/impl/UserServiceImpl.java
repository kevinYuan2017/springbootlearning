package com.kevin.springboot.learning.databasechapter5.service.impl;

import com.kevin.springboot.learning.databasechapter5.dao.MybatisUserDao;
import com.kevin.springboot.learning.databasechapter5.pojo.User;
import com.kevin.springboot.learning.databasechapter5.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private MybatisUserDao mybatisUserDao;

    @Override
    public User getUser(Long id) {
        return mybatisUserDao.getUser(id);
    }
}
