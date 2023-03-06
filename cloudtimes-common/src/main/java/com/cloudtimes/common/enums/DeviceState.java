package com.cloudtimes.common.enums;

/**
 * 设备状态
 * * 在线	0
 * * 异常	1
 * * 维护	2
 * * 下线	3
 * * 停用	4
 */
public enum DeviceState {
    Online("0", "在线"),
    Error("1", "异常"),
    Maintain("2", "维护"),
    Offline("3", "下线"),
    forbidden("4", "停用");

    private String code;
    private String msg;

    DeviceState(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
