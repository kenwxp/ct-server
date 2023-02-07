package com.cloudtimes.app.models;


import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class MessagEvent extends ApplicationEvent {

    private String context;

    public MessagEvent(Object source) {
        super(source);
    }

    public MessagEvent(Object source, Clock clock) {
        super(source, clock);
    }


    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
