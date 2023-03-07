package com.cloudtimes.enums;

/**
 * 设备类型
 */

public enum DeviceType {
    /**
     * 摄像头
     */
    CAMERA("0"),
    /**
     * 收银机
     */
    CASH("1"),
    /**
     * 门禁
     */
    DOOR_GUARD("2"),
    /**
     * 门禁刷脸
     */
    DOOR_FACE("3"),
    /**
     * nvr摄像头
     */
    NVR_CAMERA("4");

    private String code;

    DeviceType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
