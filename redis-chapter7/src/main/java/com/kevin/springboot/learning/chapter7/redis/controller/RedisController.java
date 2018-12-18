package com.kevin.springboot.learning.chapter7.redis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/redis")
public class RedisController {
    private static Logger logger = LoggerFactory.getLogger(RedisController.class);
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/stringAndHash")
    public String testStringAndHash(){
        redisTemplate.opsForValue().set("key1", "value1");
        logger.info("key1 = {}", redisTemplate.opsForValue().get("key1"));

        redisTemplate.opsForValue().set("int_key1", "1");
        logger.info("int_key1 = {}", redisTemplate.opsForValue().get("int_key1"));

        stringRedisTemplate.opsForValue().set("int", "1");
        logger.info("init_value: int = {}", stringRedisTemplate.opsForValue().get("int"));

        stringRedisTemplate.opsForValue().increment("int");
        logger.info("after_inc_stringRedisTemplate: int = {}", stringRedisTemplate.opsForValue().get("int"));

        redisTemplate.opsForValue().increment("int");
        logger.info("after_inc_redisTemplate: int = {}", redisTemplate.opsForValue().get("int"));

        stringRedisTemplate.opsForValue().decrement("int");
        logger.info("after dec: int = {}", stringRedisTemplate.opsForValue().get("int"));

        Jedis jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        jedis.decr("int");
        logger.info("after dec_jedis: int = {}", jedis.get("int"));

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("field1", "value1");
        hashMap.put("field2", "value2");
        stringRedisTemplate.opsForHash().putAll("hashMap", hashMap);
        // added new field
        stringRedisTemplate.opsForHash().put("hashMap", "field3", "value3");

        // bound hash operation
        BoundHashOperations<String, Object, Object> hashOperations = stringRedisTemplate.boundHashOps("hashMap");
        hashOperations.delete("field1", "field2", "field0");
        hashOperations.put("field4", "value4");
        hashOperations.putIfAbsent("field4", "ifAbsent");
        return (String) stringRedisTemplate.opsForHash().get("hashMap", "field4");
    }

    @GetMapping("/list")
    public String testList(){

        redisTemplate.delete("list_left_push_all");
        redisTemplate.delete("list_right_push_all");

        redisTemplate.opsForList().leftPushAll("list_left_push_all", "v1", "v2", "v3", "v4");
        Long size1 = redisTemplate.opsForList().size("list_left_push_all");
        logger.info("size1: {}", size1);
        logger.info("list_left_push_all: {}", redisTemplate.opsForList().range("list_left_push_all", 0, -2));

        redisTemplate.opsForList().rightPushAll("list_right_push_all", "v1", "v2", "v3", "v4");
        Long size2 = redisTemplate.opsForList().size("list_right_push_all");
        logger.info("size2: {}", size2);
        logger.info("list_right_push_all: {}", redisTemplate.opsForList().range("list_right_push_all", 0, -2));
        return null;
    }
}
