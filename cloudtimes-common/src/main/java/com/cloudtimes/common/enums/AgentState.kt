package com.cloudtimes.common.enums

//  0-未开通, 1-签约中, 2-已开通, 3-已停用

enum class AgentState(val code: String, val info: String) {
    None("0", "未开通"),
    Signing("1", "签约中"),
    Opened("2", "已开通"),
    Stopped("3", "已停用"),
}