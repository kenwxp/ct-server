package com.cloudtimes.mq.models;

import java.io.Serializable;

public class MessageBody implements Serializable {

    private Object payload;


    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
