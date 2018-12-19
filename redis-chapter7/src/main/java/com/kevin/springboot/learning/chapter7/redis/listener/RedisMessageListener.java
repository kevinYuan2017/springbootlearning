package com.kevin.springboot.learning.chapter7.redis.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class RedisMessageListener implements MessageListener {
    private static Logger logger = LoggerFactory.getLogger(RedisMessageListener.class);
    @Override
    public void onMessage(Message message, byte[] bytes) {

        String body = null;
        String topic = null;
        try {
            body = new String(message.getBody(), "UTF8");
            topic = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        logger.info("message body: {},  message topic: {}", body, topic);
    }
}
