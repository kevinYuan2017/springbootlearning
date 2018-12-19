package com.kevin.springboot.learning.chapter7.redis.service;

import com.kevin.springboot.learning.chapter7.redis.pojo.User;

import java.util.List;

public interface UserService {

    User insertuser(User user);

    User getUser(String id);

    User updateUser(User user);

    int deluser(String id);

    List<User> listUsers();
}
