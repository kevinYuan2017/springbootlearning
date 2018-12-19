package com.kevin.springboot.learning.chapter7.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@EnableCaching
@SpringBootApplication
public class RedisApplication {
	@Resource
	private RedisTemplate redisTemplate;
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;
	@Resource
	private MessageListener redisMessageListener;

	private ThreadPoolTaskScheduler threadPoolTaskScheduler = null;

	@Bean
	public ThreadPoolTaskScheduler initThreadPoolTaskScheduler(){
		if (threadPoolTaskScheduler == null) {
		threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
		}
		threadPoolTaskScheduler.setPoolSize(20);
		return threadPoolTaskScheduler;
	}

	@Bean
	public RedisMessageListenerContainer redisMessageListenerContainer(){
		RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
		redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
		redisMessageListenerContainer.setTaskExecutor(initThreadPoolTaskScheduler());
		redisMessageListenerContainer.addMessageListener(redisMessageListener, new ChannelTopic("topic1"));
		return redisMessageListenerContainer;
	}


	@PostConstruct
	public void initRedisTemplate(){
		RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
		redisTemplate.setKeySerializer(stringSerializer);
//		redisTemplate.setValueSerializer(stringSerializer);
		redisTemplate.setHashKeySerializer(stringSerializer);
	}

	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}

}

