package com.kevin.springboot.learning.websocketchapter13;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import javax.annotation.Resource;

@EnableScheduling
@SpringBootApplication
public class WebsocketChapter13Application {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketChapter13Application.class, args);
	}

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskScheduler.setPoolSize(5);
        threadPoolTaskScheduler.setAwaitTerminationSeconds(60);
        return threadPoolTaskScheduler;
    }

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Resource
    private MessageListener talkMessageListener;

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
        redisMessageListenerContainer.addMessageListener(talkMessageListener, new ChannelTopic("talk"));
        return redisMessageListenerContainer;
    }
}

