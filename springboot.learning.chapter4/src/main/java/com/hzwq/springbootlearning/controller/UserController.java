package com.hzwq.springbootlearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.http.codec.CodecsAutoConfiguration;
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
		User user =  this.getUser(id, username, note);
		userService.printUser(user);
		return user;
	}
	
	
	@GetMapping("/vp")
	public User validateAndPrint(String id, String username, String note) {
        User user =  this.getUser(id, username, note);
		UserValidator userValidator = (UserValidator) userService;
		if(userValidator.validate(user)) {
			userService.printUser(user);
		}
		return user;
	}

	private User getUser(String id, String username, String note){
        if (id != null || username !=  null || note !=  null){
            User user = new User();
            user.setId(id);
            user.setUsername(username);
            user.setNote(note);
            return user;
        }else {
            return null;
        }
    }
}
