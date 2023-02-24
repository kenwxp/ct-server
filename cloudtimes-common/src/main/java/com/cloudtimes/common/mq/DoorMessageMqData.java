package com.cloudtimes.common.mq;

import lombok.Data;

@Data
public class DoorMessageMqData {
    private int option; //操作类型 0-状态报文处理 1-触发报文处理
    private int deviceSerial;
    private String updateTime;

    public DoorMessageMqData() {
    }

    public DoorMessageMqData(int option, int deviceSerial, String updateTime) {
        this.option = option;
        this.deviceSerial = deviceSerial;
        this.updateTime = updateTime;
    }
}
