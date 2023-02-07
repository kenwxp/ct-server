package com.cloudtimes.common.enums

/**
 * 资产簿记类型
 *
 * @author 沈兵
 */
enum class AssetsBookType(val code: String, val info: String) {
    AgentCommission("0", "销售提成"),
    AgentActivityReward("1", "活动奖励"),
    AgentDividend("2", "营收分润"),
    AgentCashWithdraw("3", "代理提现"),
    AgentTransfer("4", "代理划账"),
    UserCashDeposit("5", "现金充值"),
    UserLuckyMoney("6", "现金红包"),
    UserShoppingPurchase("7", "购物消费"),
    // :TODO: 积分待定
    UserShoppingRefund("r", "购物退款"),
}