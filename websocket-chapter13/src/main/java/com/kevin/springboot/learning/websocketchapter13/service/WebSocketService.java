package com.kevin.springboot.learning.websocketchapter13.service;

import com.kevin.springboot.learning.websocketchapter13.event.LoginEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/ws/{username}/{password}")
@Service
public class WebSocketService {
    private static Logger LOGGER = LoggerFactory.getLogger(WebSocketService.class);
    private static int onLineCount = 0;
    private static ConcurrentHashMap<String, Session> sessionMap = new ConcurrentHashMap<>();
    private static ApplicationEventPublisher eventPublisher;

    @Autowired
    public WebSocketService(ApplicationEventPublisher publisher) {
        eventPublisher = publisher;
    }

    public WebSocketService() {
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username, @PathParam("password") String password){
        if (username != null && password != null) {
            LOGGER.info("user {} stepped in. password: {}", username, password);
        }else {
            onClose(session);
            LOGGER.warn("username or password is invalid or mismatch.");
            return;
        }
        sessionMap.put(username, session);
        addOnLineCount();
        eventPublisher.publishEvent(new LoginEvent(username));
    }

    @OnClose
    public void onClose(Session session){
        String username = session.getPathParameters().get("username");
        System.out.println("user " + username + " stepped out.");
        sessionMap.remove(username);
        subOnLineCount();
        LOGGER.info("one connection closed! Current online count is {}", getOnLineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session){
        LOGGER.info("message from client: {}", message);

        // 获取用户ID
        Map<String, String> map = session.getPathParameters();
        String username = map.get("username");
        sendMessage( sessionMap.get(username), message);
    }

    @OnError
    public void onError(Session session, Throwable error){
        LOGGER.error("ERROR!!!");
        error.printStackTrace();
    }

    private static synchronized int getOnLineCount(){
        return onLineCount;
    }

    private static synchronized void addOnLineCount(){
        onLineCount++;
    }
    private static synchronized void subOnLineCount(){
        onLineCount--;
    }
    public void sendMessage(Session clientSession, String message) {
        try {
            clientSession.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConcurrentHashMap<String, Session> getSessionMap() {
        return sessionMap;
    }
}
