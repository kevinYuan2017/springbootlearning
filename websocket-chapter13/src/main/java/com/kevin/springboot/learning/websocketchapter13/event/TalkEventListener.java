package com.kevin.springboot.learning.websocketchapter13.event;

import com.hzwq.micro.redis.Redis;
import com.kevin.springboot.learning.websocketchapter13.consts.Const;
import com.kevin.springboot.learning.websocketchapter13.service.WebSocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TalkEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(TalkEventListener.class);
    @Autowired
    private WebSocketService webSocketService;
    @Autowired
    private Redis redis;
    @EventListener
    public void talkEventListener(TalkEvent talkEvent) {
        TalkEvent.TalkMessage msg = talkEvent.getMsg();
        LOGGER.info("user: {} received a msg body: {}", msg.getUsername(), msg.getBody());
        Session clientSession = WebSocketService.getSessionMap().get(msg.getUsername());
        if (clientSession != null && clientSession.isOpen()) {
            LOGGER.info("try to send the msg to him.");
            webSocketService.sendMessage(clientSession, msg.getBody());
        }else {
            LOGGER.info("{} is off line right now, latter I will push to him.", msg.getUsername());
            Map<String, String> msgMap = new HashMap<>();
            msgMap.put(Const.USERNAME, msg.getUsername());
            msgMap.put(Const.MSGBODY, msg.getBody());
            List<Map<String, String>> userMsgList = (List<Map<String, String>>) redis.get(Const.USER_MSG);
            if (userMsgList == null) {
                userMsgList = new ArrayList<>();
            }
            userMsgList.add(msgMap);
            redis.set(Const.USER_MSG, userMsgList);
        }
    }
}
