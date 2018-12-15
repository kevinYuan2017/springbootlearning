package com.hzwq.redislearning.pojo.life;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

//@Component
public class BeanPostProcessorExample implements BeanPostProcessor {
	private static Logger logger = LoggerFactory.getLogger(BeanPostProcessorExample.class);
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		logger.info(this.getClass().getSimpleName() + " postProcessBeforeInitialization method");
		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		logger.info(this.getClass().getSimpleName() + " postProcessAfterInitialization method");
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}
	
}
