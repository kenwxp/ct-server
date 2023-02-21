package com.cloudtimes.common.enums

/**
 * 用户类型
 *
 * @author 沈兵
 */
// 0-会员, 1-代理用户, 2-店主, 3-管理员, 4-运维人员, 5-客服, 6-客服负责人
enum class UserType(val code: String, val info: String) {
    Customer("0", "会员"),
    Agent("1", "代理"),
    Shopkeeper("2", "店主"),
    Admin("3", "管理员"),
    Devops("4", "管理员"),
    Service("5", "客服"),
    PersonInChange("6", "客户负责人"),
}