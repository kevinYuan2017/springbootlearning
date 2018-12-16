package com.kevin.springboot.learning.databasechapter5.controller;

import com.kevin.springboot.learning.databasechapter5.dao.UserRepository;
import com.kevin.springboot.learning.databasechapter5.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/jpa")
public class JpaController {
    @Resource
    private UserRepository userRepository;
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable long id){
        return userRepository.findById(id);
    }
}
