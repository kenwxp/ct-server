package com.cloudtimes.common.enums

/**
 * 资产类型
 *
 * @author 沈兵
 */
enum class AssetsType(val code: String, val info: String) {
    Cash("0", "现金"),
    RewardPoints("1", "积分"),
    CreditScore("2", "信用分"),
}