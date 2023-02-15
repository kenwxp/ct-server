package com.cloudtimes.common.enums

/** 事件类型 */
enum class EventType(val code: String, val info: String) {
    SystemBulletin("0", "系统公告"),
    SystemMessage("1", "系统消息"),
    CustomerMessage("2", "会员消息"),
    StealingMessage("3", "偷盗异常"),
    RecoverMessage("4", "物品追回"),
    AgentMessage("5", "代理消息"),
    ShopkeeperMessage("6", "店主消息"),
    CustomerServiceMessage("7", "客服消息"),
}