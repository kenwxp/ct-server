package com.cloudtimes.partner.hik.service;

public interface ICtHikApiService {
    public String getAccessToken();

    public String addDevice(String deviceSerial, String validateCode);

    public String deleteDevice(String deviceSerial);

    public String getDeviceInfo(String deviceSerial);
}
