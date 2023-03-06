package com.cloudtimes.common.mq;

import lombok.Data;

import java.io.Serializable;
@Data
public class MessageBody implements Serializable {

    private String payload;

}
