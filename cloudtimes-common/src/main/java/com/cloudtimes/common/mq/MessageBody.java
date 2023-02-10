package com.cloudtimes.common.mq;

import java.io.Serializable;

public class MessageBody implements Serializable {

    private String payload;

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
