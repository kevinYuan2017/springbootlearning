package com.kevin.springboot.learning.databasechapter5.controller;

import com.kevin.springboot.learning.databasechapter5.dao.MybatisUserDao;
import com.kevin.springboot.learning.databasechapter5.pojo.User;
import com.kevin.springboot.learning.databasechapter5.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/mybatis")
public class MybatisController {
    @Resource
    private UserService userService;
    @GetMapping("/user")
    public User getUser(Long id){
        return userService.getUser(id);
    }
}
