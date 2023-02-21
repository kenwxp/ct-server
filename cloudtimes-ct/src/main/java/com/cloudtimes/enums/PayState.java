package com.cloudtimes.enums;

/**
 * 订单状态
 * 未支付	0
 * 支付中	1
 * 支付完成	2
 * 支付失败	3
 * 已撤销	4
 */
public enum PayState {
    READY_TO_PAY("0"),  // 待支付
    PAID_THEN_CONFIRM("1"),  // 支付中
    PAY_SUCCESS("2"),  // 支付成功
    PAY_FAIL("3"), // 支付失败
    CANCEL_SUCCESS("4"); // 测单成功

    private String code;

    PayState(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
