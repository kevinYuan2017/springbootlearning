package com.kevin.springboot.learning.chapter6.transaction.controller;

import com.kevin.springboot.learning.chapter6.transaction.pojo.User;
import com.kevin.springboot.learning.chapter6.transaction.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @PostMapping
    public int insertUser(User user){
        return userService.insertUser(user);
    }
}
