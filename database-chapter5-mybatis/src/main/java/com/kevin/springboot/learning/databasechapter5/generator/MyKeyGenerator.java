package com.kevin.springboot.learning.databasechapter5.generator;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
@Component
public class MyKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object o, Method method, Object... objects) {
        StringBuilder resultSB = new StringBuilder("");
        resultSB.append(o.getClass().getName())
                .append(":")
                .append(method.getName());

        for (Object param: objects){
            resultSB.append("-")
                    .append(param);
        }
        return resultSB.toString();
    }
}
