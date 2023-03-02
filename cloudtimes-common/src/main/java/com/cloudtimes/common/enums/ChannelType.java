package com.cloudtimes.common.enums;

public enum ChannelType {
    /**
     * 店家app端
     */
    MOBILE("mobile"),
    /**
     * 微信小程序端
     */
    WX_MP("wx_mp"),

    /**
     * 微信公众号
     */
    WX_OFFICIAL("wx_official"),
    /**
     * 管理后台端
     */
    WEB("web"),
    /**
     * 收银机端
     */
    CASH("cash"),
    /**
     * 支付宝小程序端
     */
    ALIPAY("alipay"),
    /**
     * 门禁刷脸端
     */
    DOOR_FACE("doorface"),
    /**
     * 线下场景
     */
    OFFLINE("offline"),
    /**
     * 服务后端
     */
    BACKEND("backend");

    private String code;

    ChannelType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
