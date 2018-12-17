package com.kevin.springboot.learning.chapter6.transaction.service;

import com.kevin.springboot.learning.chapter6.transaction.pojo.User;

import java.util.List;

public interface UserService {
    User getUser(Long id);
    int insertUser(User user);
    List<User> getAllUsers();
}
