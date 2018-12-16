package com.kevin.springboot.learning.databasechapter5.dao;

import com.kevin.springboot.learning.databasechapter5.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MybatisUserDao {
    User getUser(@Param("id") Long id);
}
