package com.kevin.springboot.learning.chapter6.transaction.dao;

import com.kevin.springboot.learning.chapter6.transaction.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Mapper
public interface UserDao {
    @Cacheable(cacheNames = "userDao:getUser", keyGenerator = "myKeyGenerator")
    User getUser(@Param("id") Long id);

    @CacheEvict(cacheNames = "userDao:getUsers", allEntries = true)
    int insertUser(User user);

    @Cacheable(cacheNames = "userDao:getUsers", keyGenerator = "myKeyGenerator")
    List<User> getUsers();
}
