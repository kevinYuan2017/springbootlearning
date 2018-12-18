package com.kevin.springboot.learning.chapter7.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {
    private RedisConnectionFactory redisConnectionFactory = null;

    @Bean(name = "redisConnectionFactory")
    public RedisConnectionFactory initRedisConnectionFactory(){
        if (this.redisConnectionFactory != null){
            return this.redisConnectionFactory;
        }
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        // max idle
        jedisPoolConfig.setMaxIdle(30);

        // max total
        jedisPoolConfig.setMaxTotal(50);

        // max wait
        jedisPoolConfig.setMaxWaitMillis(2000);

        // create jedis connectionFactory
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);

        // get standalone redis configuration
        RedisStandaloneConfiguration standaloneConfiguration = jedisConnectionFactory.getStandaloneConfiguration();

        standaloneConfiguration.setHostName("172.172.0.10");
        standaloneConfiguration.setPort(6379);
        standaloneConfiguration.setPassword("foobared123");
        standaloneConfiguration.setDatabase(0);
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(){
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        redisTemplate.setConnectionFactory(initRedisConnectionFactory());
        return redisTemplate;
    }
}
