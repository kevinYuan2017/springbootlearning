package com.kevin.springboot.learning.databasechapter5.service;

import com.kevin.springboot.learning.databasechapter5.pojo.User;

public interface UserService {
    User getUser(Long id);
}
