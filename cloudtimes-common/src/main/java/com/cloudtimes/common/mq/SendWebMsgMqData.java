package com.cloudtimes.common.mq;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SendWebMsgMqData {
    private String userId;
    private String topic;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
