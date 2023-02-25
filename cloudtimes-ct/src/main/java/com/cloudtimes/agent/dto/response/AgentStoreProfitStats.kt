package com.cloudtimes.agent.dto.response

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.math.BigDecimal

@ApiModel(value = "AgentStoreOnlineStats", description = "代理门店上线统计")
class AgentStoreProfitStats {
    @ApiModelProperty(value = "待结算分润")
    var futureDividend: BigDecimal = BigDecimal("0.00")

    @ApiModelProperty(value = "已结算分润")
    var historyDividend: BigDecimal = BigDecimal("0.00")

    @ApiModelProperty(value = "已结算佣金")
    var futureCommission: BigDecimal = BigDecimal("0.00")

    @ApiModelProperty(value = "已结算佣金")
    var historyCommission: BigDecimal = BigDecimal("0.00")
}
