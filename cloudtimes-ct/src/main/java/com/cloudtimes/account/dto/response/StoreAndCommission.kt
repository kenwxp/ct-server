package com.cloudtimes.account.dto.response

import com.cloudtimes.common.annotation.Excel
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import java.util.*

@Schema(description = "StoreAndCommission")
class StoreAndCommission {
    @Schema(description = "店铺ID")
    var id: String? = null

    @Schema(description = "门店号")
    @Excel(name = "门店号")
    var storeNo: String? = null

    @Schema(description = "店铺名称")
    var name: String? = null

    @Schema(description = "发展店铺的代理用户编号")
    var agentId: String? = null

    @Schema(description = "开店费用")
    var saleAmount: BigDecimal? = null

    @Schema(description = "开设状态")
    @Excel(name = "开设状态")
    var buildState: String? = null

    @Schema(description = "门店状态")
    var state: String? = null

    @Schema(description = "门店上线日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    var storeOnlineDate: Date? = null

    @Schema(description = "门店创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    var createDate: Date? = null

    @Schema(description = "联系人姓名")
    var contactName: String? = null

    @Schema(description = "(佣金)ID")
    var commissionId: String? = null

    @Schema(description = "(佣金)结算状态")
    var commissionState: String? = null

    @Schema(description = "(佣金)审核状态")
    var verifyState: String? = null

    @Schema(description = "(佣金)手续费费率")
    var taxRatio: BigDecimal? = null

    @Schema(description = "(佣金)结算金额")
    var amount: BigDecimal? = null

    @Schema(description = "(佣金)手续费金额")
    var taxAmount: BigDecimal? = null

    @Schema(description = "(佣金)稅前结算金额")
    var beforeTaxAmount: BigDecimal? = null
}
