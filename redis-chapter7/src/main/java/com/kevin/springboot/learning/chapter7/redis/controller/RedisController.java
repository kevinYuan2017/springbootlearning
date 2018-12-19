package com.kevin.springboot.learning.chapter7.redis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import redis.clients.jedis.Jedis;

import java.util.*;

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

//        Jedis jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
//        jedis.decr("int");
//        logger.info("after dec_jedis: int = {}", jedis.get("int"));

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

    @GetMapping("/set")
    public String testSet(){
        redisTemplate.delete("set1");
        redisTemplate.delete("set2");

        stringRedisTemplate.opsForSet().add("set1", "v1", "v1", "v2", "v3", "v4", "v50");
        stringRedisTemplate.opsForSet().add("set2",  "v2", "v4", "v6", "v80");
        Set<String> init_set1 = stringRedisTemplate.opsForSet().members("set1");
        Set<String> init_set2 = stringRedisTemplate.opsForSet().members("set2");
        logger.info("init: set1 = {}", init_set1);
        logger.info("init: set2 = {}", init_set2);

        BoundSetOperations<String, String> setOperations = stringRedisTemplate.boundSetOps("set1");
        setOperations.add("v6", "v7");
        setOperations.remove("v1", "v7");

        Long set1_size = setOperations.size();
        logger.info("set1_size = {}", set1_size);

        Set<String> set1 = setOperations.members();
        logger.info("set1 = {}", set1);

        // intersect
        Set<String> intersect = setOperations.intersect("set2");
        logger.info("intersect of set1 and set2: {}", intersect);

        // diff
        Set<String> diff = setOperations.diff("set2");
        logger.info("diff of set1 and set2: {}", diff);

        setOperations.diffAndStore("set2", "diff");
        logger.info("diff: {}", stringRedisTemplate.opsForSet().members("diff"));

        Set<String> union = setOperations.union("set2");
        logger.info("union of set1 and set2: {}", union);

        setOperations.unionAndStore("set2", "union");
        logger.info("union: {}", stringRedisTemplate.opsForSet().members("union"));
        return null;
    }

    @GetMapping("/zset")
    public String zset(){
        redisTemplate.delete("zset1");
        Set<ZSetOperations.TypedTuple<String>> typedTupleHashSet = new HashSet<>();
        for (int i = 1; i <= 10; i++){
            double sore = (i % 3 + 1) * 0.1;
            DefaultTypedTuple<String> defaultTypedTuple = new DefaultTypedTuple<>("value" + i, sore);
            typedTupleHashSet.add(defaultTypedTuple);
        }

        stringRedisTemplate.opsForZSet().add("zset1", typedTupleHashSet);

//        stringRedisTemplate.opsForZSet().add("zset1", "value1", 0.11);
//        stringRedisTemplate.opsForZSet().add("zset1", "value2", 0.41);
//        stringRedisTemplate.opsForZSet().add("zset1", "value3", 0.31);
//        stringRedisTemplate.opsForZSet().add("zset1", "value4", 0.41);
//        stringRedisTemplate.opsForZSet().add("zset1", "value5", 0.41);
//        stringRedisTemplate.opsForZSet().add("zset1", "value6", 0.41);
//        stringRedisTemplate.opsForZSet().add("zset1", "value7", 0.31);
//        stringRedisTemplate.opsForZSet().add("zset1", "value8", 0.41);
//        stringRedisTemplate.opsForZSet().add("zset1", "value9", 0.71);
//        stringRedisTemplate.opsForZSet().add("zset1", "value10", 0.81);

        BoundZSetOperations<String, String> zSetOps = stringRedisTemplate.boundZSetOps("zset1");
        Set<String> scores = zSetOps.rangeByScore(0, 1);
        logger.info("scores: {}", scores);

        RedisZSetCommands.Range range = new RedisZSetCommands.Range();
//        range.gt("value3");
        range.gte("value2");
//        range.lte("value8");
        Set<String> rangeByLex = zSetOps.rangeByLex(range);
        logger.info("rangeByLex: {}", rangeByLex);
        Set<String> rangeByLex1 = stringRedisTemplate.opsForZSet().rangeByLex("zset1", RedisZSetCommands.Range.range().gte("value4"));
        logger.info("rangeByLex1: {}", rangeByLex1);

        Cursor<ZSetOperations.TypedTuple<String>> tupleCursor = stringRedisTemplate.opsForZSet().scan("zset1", ScanOptions.scanOptions().build());
        tupleCursor.forEachRemaining((ZSetOperations.TypedTuple tuple) -> {
            logger.info("value: {}, score: {}", tuple.getValue(), tuple.getScore());
        });
        return null;
    }
}
