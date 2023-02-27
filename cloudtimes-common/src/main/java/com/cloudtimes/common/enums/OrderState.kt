package com.cloudtimes.common.enums

/**
 * 订单状态
 *
 * 0-未支付, 1-支付中, 2-支付完成, 3-支付失败, 4-已撤销
 **/
enum class OrderState(val code: String, val info: String) {
    None("0", "未支付"),
    Paying("1", "支付中"),
    Success("2", "支付完成"),
    Fail("3", "支付失败"),
    Canceled("4", "已撤销"),
}
