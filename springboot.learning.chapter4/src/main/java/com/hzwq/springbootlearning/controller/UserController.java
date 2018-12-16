package com.hzwq.springbootlearning.controller;

import com.hzwq.springbootlearning.pojo.User;
import com.hzwq.springbootlearning.service.UserService;
import com.hzwq.springbootlearning.service.UserValidator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
	// 注入用户服务
	@Resource
	private UserService userService;
	
	@GetMapping("/print")
	public User printUser(String id, String username, String note) {
		User user =  this.getUser(id, username, note);
		userService.printUser(user);
		return user;
	}
	
	
	@GetMapping("/vp")
	public User validateAndPrint(String id, String username, String note) {
        User user =  this.getUser(id, username, note);
		UserValidator userValidator = (UserValidator) userService;
		if(userValidator.validate(user) && userValidator.validateUserName(user)) {
			userService.printUser(user);
		}
		return user;
	}

	private User getUser(String id, String username, String note){
        return id != null || username != null || note != null ? new User(id, username, note) : null;
    }
}
