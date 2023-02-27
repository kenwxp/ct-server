package com.cloudtimes.common.enums

enum class SettlementState(val code: String, val info: String) {
    None("0", "未结算"),
    Success("1", "结算成功"),
    Fail("2", "结算失败"),
}