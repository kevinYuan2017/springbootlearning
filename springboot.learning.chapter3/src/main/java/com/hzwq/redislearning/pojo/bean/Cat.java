package com.hzwq.redislearning.pojo.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hzwq.redislearning.pojo.inf.IAnimal;
@Component
public class Cat implements IAnimal {
	@Value("猫")
	private String name;
	@Value("抓老鼠")
	private String usage;
	private static Logger logger = LoggerFactory.getLogger(Cat.class);
	
	@Override
	public void use() {
		logger.info(this.name + this.getClass().getSimpleName() + "是用来" + this.usage + "的！");
	}
}
