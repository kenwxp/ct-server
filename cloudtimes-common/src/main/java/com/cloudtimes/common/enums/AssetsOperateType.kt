package com.cloudtimes.common.enums

/**
 * 资产操作类型
 *
 * @author 沈兵
 */
enum class AssetsOperateType(val code: String, val info: String) {
    Increase("0", "增加"),
    Decrease("1", "扣减"),
}