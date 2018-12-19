package com.kevin.springboot.learning.chapter7.redis.service.impl;

import com.kevin.springboot.learning.chapter7.redis.dao.UserDao;
import com.kevin.springboot.learning.chapter7.redis.pojo.User;
import com.kevin.springboot.learning.chapter7.redis.service.UserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Override
    @CachePut(cacheNames = "userDao:getUser", key = "#user.id")
    @CacheEvict(cacheNames = "userDao:listUsers", key = "'userList'")
    public User insertuser(User user) {
        userDao.insertUser(user);
        return user;
    }

    @Override
    @Cacheable(cacheNames = "userDao:getUser", key = "#id")
    public User getUser(String id) {
        return userDao.getUser(id);
    }

    @Override
    @CachePut(cacheNames = "userDao:getUser", key = "#user.id")
    @CacheEvict(cacheNames = "userDao:listUsers", key = "'userList'")
    public User updateUser(User user) {
        userDao.updateUser(user);
        return user;
    }

    @Override
    @CachePut(cacheNames = "userDao:getUser", key = "#id")
    @CacheEvict(cacheNames ="userDao:listUsers", key = "'userList'")
    public void delUser(String id) {
        userDao.delUser(id);
    }

    @Override
    @Cacheable(cacheNames = "userDao:listUsers", key = "'userList'")
    public List<User> listUsers() {
        return userDao.listUsers();
    }
}
