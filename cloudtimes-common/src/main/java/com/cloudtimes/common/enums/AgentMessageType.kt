package com.cloudtimes.common.enums

/** 代理消息类型 */
enum class AgentMessageType(val code: String, val info: String) {
    SystemMessage("51", "系统消息"),
    CommissionMessage("52", "佣金消息"),
    ActivityMessage("53", "活动消息"),
    DividendMessage("54", "分润消息"),
    WithdrawMessage("55", "提现消息")
}