package com.kevin.springboot.learning.websocketchapter13.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/ws")
@Service
public class WebSocketServiceImpl {
    private static Logger logger = LoggerFactory.getLogger(WebSocketServiceImpl.class);
    private static int onLineCount = 0;
    private static CopyOnWriteArraySet<WebSocketServiceImpl> webSocketSet = new CopyOnWriteArraySet<>();
    private Session session;

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);
        addOnLineCount();
    }

    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        subOnLineCount();
        logger.info("one connection closed! Current online count is {}", getOnLineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session){
        logger.info("message from client: {}", message);

        for (WebSocketServiceImpl item: webSocketSet){
            try {
                String userName = item.session.getUserPrincipal().getName();
                logger.info("userName: {}", userName);
                item.sendMessage(message);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error){
        logger.error("ERROR!!!");
        error.printStackTrace();
    }

    private static synchronized int getOnLineCount(){
        return onLineCount;
    }

    private static synchronized void addOnLineCount(){
        WebSocketServiceImpl.onLineCount++;
    }
    private static synchronized void subOnLineCount(){
        WebSocketServiceImpl.onLineCount--;
    }
    private void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
    }
}
