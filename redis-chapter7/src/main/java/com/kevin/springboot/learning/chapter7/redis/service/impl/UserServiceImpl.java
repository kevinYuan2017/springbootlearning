package com.kevin.springboot.learning.chapter7.redis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kevin.springboot.learning.chapter7.redis.dao.UserDao;
import com.kevin.springboot.learning.chapter7.redis.pojo.User;
import com.kevin.springboot.learning.chapter7.redis.service.UserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
    @Caching(
            put = @CachePut(cacheNames = "userDao:getUser", key = "#user.id"),
            evict = @CacheEvict(cacheNames = "userDao:listUsers", key = "'userList'")
    )
    public User insertUser(User user) {
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
    @Caching(
            put = @CachePut(cacheNames = "userDao:getUser", key = "#id"),
            evict = @CacheEvict(cacheNames = "userDao:listUsers", key = "'userList'")
    )
    public void delUser(String id) {
        userDao.delUser(id);
    }

    @Override
    @Cacheable(cacheNames = "userDao:listUsers", key = "'userList'")
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    @Cacheable(cacheNames = "userDao:userPage", key = "'userPage:' + #pageNum + '/' + #pageSize")
    public List<User> userPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userDao.listUsers();
        PageInfo<User> userPageInfo = new PageInfo<User>(userList);
        return userPageInfo.getList();
    }
}
