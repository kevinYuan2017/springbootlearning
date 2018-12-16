package com.hzwq.springbootlearning.aspect;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(0)
@Component
@Aspect
public class MyAspect3 {
	private static Logger logger = LoggerFactory.getLogger(MyAspect3.class);
	
	@Pointcut("execution(* com.hzwq.springbootlearning.service.impl.UserServiceImpl.multiAspects(..))")
	public void multiAspects() {
	}
	
	@Before("multiAspects()")
	public void before() {
		logger.info("multiAspects3 before ...");
	}
	
	@After("multiAspects()")
	public void after() {
		logger.info("multiAspects3 after ...");
	}
	
	@AfterReturning("multiAspects()")
	public void afterReturning() {
		logger.info("multiAspects3 afterReturning ...");
	}

	@AfterThrowing("multiAspects()")
	public void afterThrowing() {
		logger.info("multiAspects3 afterThrowing ...");
	}
}
