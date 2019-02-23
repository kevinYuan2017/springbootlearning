package com.kevin.springboot.learning.websocketchapter13.event;

import org.springframework.context.ApplicationEvent;

public class LoginEvent extends ApplicationEvent {
    private String username;
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public LoginEvent(Object source) {
        super(source);
        this.username = (String) source;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
