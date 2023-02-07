package com.cloudtimes.serving.door.service;

import com.cloudtimes.hardwaredevice.domain.CtDevice;

public interface ICtDoorFaceLoginService {
    public boolean checkDoorFaceNew(String deviceSerial);

    public CtDevice doorFaceLogin(String deviceSerial, String deviceName, String shopNo);
}
