package com.hzwq.springbootlearning.aspect;

import com.hzwq.springbootlearning.pojo.User;
import com.hzwq.springbootlearning.service.UserValidator;
import com.hzwq.springbootlearning.service.impl.UserValidatorImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect1 {
	private static Logger logger = LoggerFactory.getLogger(MyAspect1.class);
	
	@Pointcut("execution(* com.hzwq.springbootlearning.service.impl.UserServiceImpl.multiAspects(..))")
	public void multiAspects() {
	}
	
	@Before("multiAspects()")
	public void before() {
		logger.info("multiAspects1 before ...");
	}
	
	@After("multiAspects()")
	public void after() {
		logger.info("multiAspects1 after ...");
	}
	
	@AfterReturning("multiAspects()")
	public void afterReturning() {
		logger.info("multiAspects1 afterReturning ...");
	}

	@AfterThrowing("multiAspects()")
	public void afterThrowing() {
		logger.info("multiAspects1 afterThrowing ...");
	}
}
