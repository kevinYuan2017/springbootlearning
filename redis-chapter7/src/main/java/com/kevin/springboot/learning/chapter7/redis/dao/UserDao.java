package com.kevin.springboot.learning.chapter7.redis.dao;

import com.kevin.springboot.learning.chapter7.redis.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;


@Mapper
public interface UserDao {

    int insertUser(User user);

    User getUser(String id);

    int updateUser(User user);

    int delUser(String id);

    List<User> listUsers();
}
