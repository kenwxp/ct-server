package com.cloudtimes.account.dto.response

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.math.BigDecimal

@ApiModel(value = "QueryStoreProfitResponse", description = "查询代理门店收益应答体")
open class QueryStoreProfitResponse {
    @ApiModelProperty(value = "待结算分润")
    var futureDividend: BigDecimal = BigDecimal("0.00")

    @ApiModelProperty(value = "已结算分润")
    var historyDividend: BigDecimal = BigDecimal("0.00")

    @ApiModelProperty(value = "已结算佣金")
    var futureCommission: BigDecimal = BigDecimal("0.00")

    @ApiModelProperty(value = "已结算佣金")
    var historyCommission: BigDecimal = BigDecimal("0.00")
}
