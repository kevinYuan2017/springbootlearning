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
import java.util.List;
import java.util.Map;

@Component
public class LoginEventListener  {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginEventListener.class);
    @Autowired
    private Redis redis;
    @Autowired
    private WebSocketService webSocketService;

    @EventListener
    public void loginEventListener(LoginEvent loginEvent) {
        sendCacheMsg(loginEvent.getUsername());
    }

    private void sendCacheMsg(String username) {
        List<Map<String, String>> userMsgList = (List<Map<String, String>>) redis.get(Const.USER_MSG);
        List<Map<String, String>> newUserMsgList = new ArrayList<>();
        if (userMsgList != null && userMsgList.size() != 0) {
            for (Map<String, String> msgMap : userMsgList) {
                if (username.equals(msgMap.get(Const.USERNAME))) {
                    String msgBody = msgMap.get(Const.MSGBODY);
                    Session session = WebSocketService.getSessionMap().get(username);
                    LOGGER.info("pushing cache msg to user: {}, msg: {}", username, msgBody);
                    webSocketService.sendMessage(session, msgMap.get(Const.MSGBODY));
                }else {
                    newUserMsgList.add(msgMap);
                }
            }
            redis.set(Const.USER_MSG, newUserMsgList);
        }
    }
}
