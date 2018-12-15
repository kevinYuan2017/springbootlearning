package com.hzwq.springbootlearning.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hzwq.springbootlearning.service.UserValidator;
import com.hzwq.springbootlearning.service.impl.UserValidatorImpl;

@Component
@Aspect
public class MyAspect {
	private static Logger logger = LoggerFactory.getLogger(MyAspect.class);
	
	@DeclareParents(
			value = "com.hzwq.springbootlearning.service.impl.UserServiceImpl+", 
			defaultImpl = UserValidatorImpl.class)
	public UserValidator userValidator;
	
	@Pointcut("execution(* com.hzwq.springbootlearning.service.impl.UserServiceImpl.printUser(..))")
	public void pointCut() {
	}
	
	@Before("pointCut()")
	public void before() {
		logger.info("before ...");
	}
	
	@Around("pointCut()")
	public void around(ProceedingJoinPoint jp) throws Throwable {
		logger.info("around before ...");
		jp.proceed();
		logger.info("around after ...");
	}
	
	@After("pointCut()")
	public void after() {
		logger.info("after ...");
	}
	
	@AfterReturning("pointCut()")
	public void afterReturning() {
		logger.info("afterReturning ...");
	}
	
	@AfterThrowing("pointCut()")
	public void afterThrowing() {
		logger.info("afterThrowing ...");
	}
}
