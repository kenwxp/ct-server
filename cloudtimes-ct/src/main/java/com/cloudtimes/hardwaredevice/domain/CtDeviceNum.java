package com.cloudtimes.hardwaredevice.domain;

import lombok.Data;

@Data
public class CtDeviceNum {
    private String storeId;
    private int total;
    private int online;
    private int offline;
}
