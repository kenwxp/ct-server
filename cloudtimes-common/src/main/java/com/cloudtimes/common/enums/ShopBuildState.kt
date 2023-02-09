package com.cloudtimes.common.enums

/** 门店状态 */
enum class ShopBuildState(val code: String, val info: String) {
    Applying("0", "申请中"),
    Signing("1", "签约中"),
    Online("2", "已上线"),
    Offline("3", "已下线"),
}
