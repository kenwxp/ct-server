package com.cloudtimes.app.domain;

import com.cloudtimes.hardwaredevice.domain.CtDevice;
import lombok.Data;

@Data
public class DetectionData {
    private int option;  // 0-loadData 1-device
    private CtDevice device;
}
