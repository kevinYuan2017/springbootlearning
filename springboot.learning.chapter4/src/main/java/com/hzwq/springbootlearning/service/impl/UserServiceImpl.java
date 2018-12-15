package com.hzwq.springbootlearning.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.hzwq.springbootlearning.pojo.User;
import com.hzwq.springbootlearning.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Override
	public void printUser(User user) {
		Assert.notNull(user, "请检查用户参数是否为空...");
		logger.info("User = {}", user.toString());
	}

}
