package com.cloudtimes.common.mq;

import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.common.enums.OpenDoorOption;
import lombok.Data;

@Data
public class OpenDoorMqData {
    private OpenDoorOption option; // 操作类型
    private String storeId;
    private String userId;
    private ChannelType channelType;
    private boolean once;
    private String beginTime;
    private String endTime;

    public OpenDoorMqData() {
    }

    public OpenDoorMqData(OpenDoorOption option) {
        this.option = option;
    }

    public OpenDoorMqData(OpenDoorOption option, String storeId, String userId, ChannelType channelType) {
        this.option = option;
        this.storeId = storeId;
        this.userId = userId;
        this.channelType = channelType;
    }
}
