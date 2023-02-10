package com.cloudtimes.common.mq;

import org.springframework.context.ApplicationEvent;

import java.io.Serializable;
import java.time.Clock;

public class MessageBody implements Serializable {

    private Object payload;

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
