package com.cloudtimes.mq.domain;

import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.enums.DoorOpType;
import lombok.Data;

@Data
public class DoorOpData {
    private DoorOpType option; // 操作类型
    private String storeId;
    private String userId;
    private ChannelType channelType;
    private boolean once;
    private String beginTime;
    private String endTime;
}
