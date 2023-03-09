package com.cloudtimes.partner.hik.domain;

public class HikConstant {
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";
    public static final String hikHost = "open.ys7.com";
    public static final String getAccessTokenUri = "/api/lapp/token/get";
    public static final String addDeviceUri = "/api/lapp/device/add";
    public static final String deleteDeviceUri = "/api/lapp/device/delete";
    public static final String addDeviceIPCUri = "/api/lapp/device/ipc/add";
    public static final String deleteDeviceIPCUri = "/api/lapp/device/ipc/delete";
    public static final String getDeviceListUri = "/api/lapp/device/list";
    public static final String getDeviceInfoUri = "/api/lapp/device/info";
    public static final String getDeviceCaptureUri = "/api/lapp/device/capture";
    public static final String getLiveAddressUri = "/api/lapp/v2/live/address/get";
    public static final String setDeviceEncryptOffUri = "/api/lapp/device/encrypt/off";
    public static final String setDeviceEncryptOnUri = "/api/lapp/device/encrypt/on";
    public static final String getNvrChannelStatus = "/api/v3/open/device/metadata/channel/status";
}
