package com.kevin.springboot.learning.websocketchapter13.event;

import org.springframework.context.ApplicationEvent;

public class TalkEvent extends ApplicationEvent {
    private TalkMessage msg;
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public TalkEvent(Object source) {
        super(source);
        this.msg = new TalkMessage((String) source);
    }

    public TalkMessage getMsg() {
        return msg;
    }

    public void setMsg(TalkMessage msg) {
        this.msg = msg;
    }

    public class TalkMessage {
        private String username;
        private String body;

        public TalkMessage(String msg) {
            int index = msg.indexOf(":");
            this.username = msg.substring(0, index);
            this.body = msg.substring(index + 1);
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }
}
