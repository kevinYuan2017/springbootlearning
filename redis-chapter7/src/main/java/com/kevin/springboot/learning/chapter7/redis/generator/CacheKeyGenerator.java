package com.kevin.springboot.learning.chapter7.redis.generator;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class CacheKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object o, Method method, Object... objects) {
        StringBuilder paramStr = new StringBuilder("params");
        for (Object param: objects
             ) {
            paramStr.append(":" ).append(param);
        }
        return paramStr.toString();
    }
}
