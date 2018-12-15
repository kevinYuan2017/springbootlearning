package com.hzwq.springbootlearning.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.hzwq.springbootlearning.service.HelloService;

@Component("helloService")
public class HelloServiceImpl implements HelloService {
	private static Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);
	@Override
	public void sayHello(String name) {
		Assert.isNull(name, "parameter is null!!!");
		logger.info("Hello, {}", name);
	}

}
