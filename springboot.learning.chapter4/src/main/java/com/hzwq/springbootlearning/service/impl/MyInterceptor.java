package com.hzwq.springbootlearning.service.impl;

import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hzwq.springbootlearning.invoke.Invocation;
import com.hzwq.springbootlearning.service.Interceptor;

public class MyInterceptor implements Interceptor {
	
	private static Logger logger = LoggerFactory.getLogger(MyInterceptor.class);
	
	@Override
	public boolean before() {
		logger.info("before ...");
		return true;
	}

	@Override
	public boolean after() {
		logger.info("after ...");
		return true;
	}

	@Override
	public Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
		logger.info("around before ...");
		Object object = invocation.proceed();
		logger.info("around after ...");
		return object;
	}
	
	@Override
	public void afterReturning() {
		logger.info("afterReturning ...");
	}

	@Override
	public void afterThrowing() {
		logger.info("afterThrowing ...");
	}

	@Override
	public boolean useAround() {
		return true;
	}
}
