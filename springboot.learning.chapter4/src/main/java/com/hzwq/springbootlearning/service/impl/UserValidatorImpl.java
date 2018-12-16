package com.hzwq.springbootlearning.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hzwq.springbootlearning.pojo.User;
import com.hzwq.springbootlearning.service.UserValidator;

@Service("userValidator")
public class UserValidatorImpl implements UserValidator {
	private static Logger logger = LoggerFactory.getLogger(UserValidatorImpl.class);
	@Override
	public boolean validate(User user) {
		logger.info("引入新的接口: " + UserValidator.class.getSimpleName());
		if (user != null){
			logger.info("user is not null, user info validate pass");
			return true;
		}else {
			logger.warn("user is null, no need to continue");
			return false;
		}
	}

	@Override
	public boolean validateUserName(User user) {
		if (user.getUsername() != null){
			logger.info("username is not null, validate pass");
			return true;
		}else {
			logger.warn("username is null, validate failed");
			return false;
		}
	}
}
