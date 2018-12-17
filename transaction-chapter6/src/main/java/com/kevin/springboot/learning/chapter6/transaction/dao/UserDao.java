package com.kevin.springboot.learning.chapter6.transaction.dao;

import com.kevin.springboot.learning.chapter6.transaction.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

@Mapper
@CacheConfig(cacheNames = "userDao")
public interface UserDao {
    @Cacheable(keyGenerator = "myKeyGenerator")
    User getUser(@Param("id") Long id);
    int insertUser(User user);
}
