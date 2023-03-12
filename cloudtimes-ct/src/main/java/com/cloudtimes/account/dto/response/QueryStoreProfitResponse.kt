package com.cloudtimes.account.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

@Schema(description = "查询代理门店收益应答体")
open class QueryStoreProfitResponse {
    @Schema(description = "待结算分润")
    var futureDividend: BigDecimal = BigDecimal("0.00")

    @Schema(description = "已结算分润")
    var historyDividend: BigDecimal = BigDecimal("0.00")

    @Schema(description = "已结算佣金")
    var futureCommission: BigDecimal = BigDecimal("0.00")

    @Schema(description = "已结算佣金")
    var historyCommission: BigDecimal = BigDecimal("0.00")
}
