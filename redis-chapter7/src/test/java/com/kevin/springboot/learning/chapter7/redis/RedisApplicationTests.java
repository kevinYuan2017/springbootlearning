package com.kevin.springboot.learning.chapter7.redis;

import com.kevin.springboot.learning.chapter7.redis.config.RedisConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {
	private static Logger logger = LoggerFactory.getLogger(RedisApplicationTests.class);
	@Test
	public void contextLoads() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(RedisConfig.class);
		RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
		redisTemplate.opsForValue().set("gqq", "shabi");
		Object gqq = redisTemplate.opsForValue().get("gqq");
		logger.info("gqq from redis: {}", gqq);
	}

}

