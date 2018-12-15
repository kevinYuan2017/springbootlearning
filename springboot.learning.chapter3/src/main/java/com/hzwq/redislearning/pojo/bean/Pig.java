package com.hzwq.redislearning.pojo.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.hzwq.redislearning.pojo.inf.IAnimal;

@Primary
@Component
public class Pig implements IAnimal {
	@Value("猪")
	private String name = "猪";
	@Value("吃")
	private String usage = "吃";
	private static Logger logger = LoggerFactory.getLogger(Pig.class);
	@Override
	public void use() {
		logger.info(this.name + this.getClass().getSimpleName() + "是用来" + this.usage + "的！");
	}
}
