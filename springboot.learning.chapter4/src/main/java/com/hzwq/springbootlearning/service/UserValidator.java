package com.hzwq.springbootlearning.service;

import com.hzwq.springbootlearning.pojo.User;

public interface UserValidator {
	boolean validate(User user);
}
