package com.hzwq.redislearning.pojo.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.hzwq.redislearning.pojo.inf.IAnimal;
import com.hzwq.redislearning.pojo.inf.IMan;

@Component
@Lazy
public class CatMan implements IMan {
	private static Logger logger = LoggerFactory.getLogger(CatMan.class);
	private IAnimal iAnimal;
	
	public CatMan(@Autowired @Qualifier(value = "cat") IAnimal iAnimal) {
		super();
		logger.warn("lazy init");
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

}
