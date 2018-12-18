package com.kevin.springboot.learning.chapter7.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@SpringBootApplication
public class RedisApplication {
	@Resource
	private RedisTemplate redisTemplate;

	@PostConstruct
	public void initRedisTemplate(){
		RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
		redisTemplate.setKeySerializer(stringSerializer);
		redisTemplate.setValueSerializer(stringSerializer);
		redisTemplate.setHashKeySerializer(stringSerializer);
	}

	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}

}

