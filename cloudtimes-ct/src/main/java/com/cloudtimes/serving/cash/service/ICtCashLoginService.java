package com.cloudtimes.serving.cash.service;

import com.cloudtimes.hardwaredevice.domain.CtDevice;

public interface ICtCashLoginService {

    public boolean checkCashNew(String deviceSerial);

    public CtDevice cashLogin(String deviceSerial, String deviceName, String shopNo);
}
