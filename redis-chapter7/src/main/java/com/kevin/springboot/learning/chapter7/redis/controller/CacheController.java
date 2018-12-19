package com.kevin.springboot.learning.chapter7.redis.controller;

import com.kevin.springboot.learning.chapter7.redis.pojo.User;
import com.kevin.springboot.learning.chapter7.redis.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cache")
public class CacheController {
    @Resource
    private UserService userService;
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable String id){
        return userService.getUser(id);
    }

    @GetMapping("/user/list")
    public List<User> userList(){
        return userService.listUsers();
    }

    @DeleteMapping("/user/{id}")
    public int delUser(@PathVariable String id){
        return userService.deluser(id);
    }

    @PostMapping("/user")
    public User addUser(User recUser){
        User tmpUser = new User();
        tmpUser.setId(UUID.randomUUID().toString());
        tmpUser.setName(recUser.getName());
        tmpUser.setNote(recUser.getNote());
        return userService.insertuser(tmpUser);
    }

    @PutMapping("/user")
    public User updateUser(User user){
        return userService.updateUser(user);
    }

}
