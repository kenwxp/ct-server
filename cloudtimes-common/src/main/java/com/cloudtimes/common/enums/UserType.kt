package com.cloudtimes.common.enums

/**
 * 用户类型
 *
 * @author 沈兵
 */
enum class UserType(val code: String, val info: String) {
    Customer("0", "会员"),
    Agent("1", "代理"),
    AgentDividend("2", "店主"),
}