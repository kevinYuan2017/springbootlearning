package com.kevin.springboot.learning.chapter7.redis;

import com.kevin.springboot.learning.chapter7.redis.config.RedisConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {
	private static Logger logger = LoggerFactory.getLogger(RedisApplicationTests.class);
	@Resource
	private RedisTemplate redisTemplate;
	@Test
	public void contextLoads() {
//		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(RedisApplication.class);
//		RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
		redisTemplate.opsForValue().set("gqq2", "shabi");
		Object gqq = redisTemplate.opsForValue().get("gqq2");
		logger.info("gqq from redis: {}", gqq);
	}

	@Test
	public void useRedisCallback(){
		redisTemplate.execute((RedisConnection conn) -> {
			conn.set("gqq".getBytes(), "shaniu".getBytes());
			conn.set("yzk".getBytes(), "shadan".getBytes());
			return null;
		});
	}

	@Test
    public void useSessionCallback(){
	    redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisOperations.opsForValue().set("gqq", "shaniu");
                redisOperations.opsForValue().set("yzk", "shadan");
                return null;
            }
        });
    }

}

