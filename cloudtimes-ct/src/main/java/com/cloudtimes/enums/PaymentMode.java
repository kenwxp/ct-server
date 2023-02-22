package com.cloudtimes.enums;

/**
 * 支付方式
 * 支付宝	0
 * 微信	1
 * 网银	2
 */
public enum PaymentMode {
    ALI_PAY("0"),

    WECHAT_PAY("1"),

    E_BANK("2");

    private String code;

    PaymentMode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
