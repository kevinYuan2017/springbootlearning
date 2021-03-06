package com.kevin.springboot.learning.databasechapter5.dao;

import com.kevin.springboot.learning.databasechapter5.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

@CacheConfig(cacheNames = "mybatisUserDao")
@Mapper
public interface MybatisUserDao {

    @Cacheable(keyGenerator = "myKeyGenerator")
    User getUser(@Param("id") Long id);
}
