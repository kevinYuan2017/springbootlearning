package com.kevin.springboot.learning.databasechapter5.service;

import com.kevin.springboot.learning.databasechapter5.pojo.User;

import java.util.List;

public interface JdbcTmplateUserService {
    User getUser(long id);
    User getUser2(long id);
    User getUser3(long id);
    public List<User> findUsers(String name, String note);
    public int insertUser(User user);
    public int updateUser(User user);
    public int deleteUser(long id);
}
