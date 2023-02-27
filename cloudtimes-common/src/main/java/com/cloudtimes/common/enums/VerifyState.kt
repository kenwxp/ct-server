package com.cloudtimes.common.enums

/** 门店状态 */
enum class VerifyState(val code: String, val info: String) {
    None("0", "未审核"),
    Agent("1", "已确认"),
    Platform("2", "已审核"),
}
