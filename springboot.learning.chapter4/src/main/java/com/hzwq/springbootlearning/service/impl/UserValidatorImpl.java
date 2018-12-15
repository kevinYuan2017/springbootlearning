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
		return user != null;
	}

}
