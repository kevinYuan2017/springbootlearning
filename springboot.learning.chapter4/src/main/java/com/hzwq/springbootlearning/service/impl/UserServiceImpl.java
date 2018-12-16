package com.hzwq.springbootlearning.service.impl;

import com.hzwq.springbootlearning.annotation.UserValidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.hzwq.springbootlearning.pojo.User;
import com.hzwq.springbootlearning.service.UserService;


@Service("userService")
public class UserServiceImpl implements UserService {
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@UserValidate
	@Override
	public void printUser(User user) {
		Assert.notNull(user, "请检查用户参数是否为空...");
		logger.info("User = {}", user.toString());
	}

	@Override
	public void multiAspects() {
		logger.info("multi aspects sequence test");
	}

}
