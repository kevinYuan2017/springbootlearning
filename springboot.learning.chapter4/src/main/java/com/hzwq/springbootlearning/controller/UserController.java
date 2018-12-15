package com.hzwq.springbootlearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hzwq.springbootlearning.pojo.User;
import com.hzwq.springbootlearning.service.UserService;
import com.hzwq.springbootlearning.service.UserValidator;

@RestController
@RequestMapping("/user")
public class UserController {
	// 注入用户服务
	@Autowired
	private UserService userService;
	
	@GetMapping("/print")
	public User printUser(String id, String username, String note) {
		User user = new User();
		user.setId(id);
		user.setUsername(username);
		user.setNote(note);
		userService.printUser(user);
		return user;
	}
	
	
	@GetMapping("/vp")
	public User validateAndPrint(String id, String username, String note) {
		User user = new User();
		user.setId(id);
		user.setUsername(username);
		user.setNote(note);
		UserValidator userValidator = (UserValidator) userService;
		if(userValidator.validate(user)) {
			userService.printUser(user);
		}
		return user;
	}
}
