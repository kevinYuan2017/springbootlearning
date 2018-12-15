package com.hzwq.redislearning.pojo.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.hzwq.redislearning.pojo.inf.IAnimal;
import com.hzwq.redislearning.pojo.inf.IMan;

@Component
public class PigMan implements IMan, BeanNameAware, BeanFactoryAware,
	ApplicationContextAware, InitializingBean, DisposableBean {
	private static Logger logger = LoggerFactory.getLogger(PigMan.class);
	private IAnimal iAnimal;
	
	public PigMan(@Autowired IAnimal iAnimal) {
		super();
		this.iAnimal = iAnimal;
	}

	@Override
	public void service() {
		this.iAnimal.use();
	}

	@Override
	public void setAnimal(IAnimal iAnimal) {
		this.iAnimal = iAnimal;
	}
	
	@PostConstruct
	public void init() {
		logger.info(this.getClass().getSimpleName() + " @PostConstruct init method");
	}
	
	@PreDestroy
	public void preDestroy() throws Exception {
		logger.info(this.getClass().getSimpleName() + " @PreDestroy preDestroy method");
	}
	
	@Override
	public void destroy() throws Exception {
		logger.info(this.getClass().getSimpleName() + " destroy method");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info(this.getClass().getSimpleName() + " afterPropertiesSet method");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		logger.info(this.getClass().getSimpleName() + " setApplicationContext method");
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		logger.info(this.getClass().getSimpleName() + " setBeanFactory method");
	}

	@Override
	public void setBeanName(String name) {
		logger.info(this.getClass().getSimpleName() + " setBeanName method");
	}

}
