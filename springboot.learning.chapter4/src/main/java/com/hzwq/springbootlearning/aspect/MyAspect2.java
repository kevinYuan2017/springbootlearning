package com.hzwq.springbootlearning.aspect;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(-1)
@Component
@Aspect
public class MyAspect2 {
	private static Logger logger = LoggerFactory.getLogger(MyAspect2.class);
	
	@Pointcut("execution(* com.hzwq.springbootlearning.service.impl.UserServiceImpl.multiAspects(..))")
	public void multiAspects() {
	}
	
	@Before("multiAspects()")
	public void before() {
		logger.info("multiAspects2 before ...");
	}
	
	@After("multiAspects()")
	public void after() {
		logger.info("multiAspects2 after ...");
	}
	
	@AfterReturning("multiAspects()")
	public void afterReturning() {
		logger.info("multiAspects2 afterReturning ...");
	}

	@AfterThrowing("multiAspects()")
	public void afterThrowing() {
		logger.info("multiAspects2 afterThrowing ...");
	}
}
