package com.cloudtimes.common.mq;

import lombok.Data;

@Data
public class DoorStateData {
    private int deviceSerial;
    private String updateTime;

    public DoorStateData() {
    }

    public DoorStateData(int deviceSerial, String updateTime) {
        this.deviceSerial = deviceSerial;
        this.updateTime = updateTime;
    }
}
