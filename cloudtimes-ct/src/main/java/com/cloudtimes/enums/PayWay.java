package com.cloudtimes.enums;

/**
 * 支付渠道类型 0-支付宝 1-微信 2-收钱吧 3-汇天下
 */
public enum PayWay {
    ALI_PAY("0"),
    WECHAT_PAY("1"),
    SHOU_QIAN_BA("2"),
    HUI_TIAN_XIA("3");

    private String code;

    PayWay(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
