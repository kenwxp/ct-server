package com.cloudtimes.agent.dto.response

import com.cloudtimes.agent.domain.CtUserAgent
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.math.BigDecimal

@ApiModel(value = "AgentAssets", description = "代理资产")
class AgentAssets: CtUserAgent() {
    @ApiModelProperty(value = "待结算收入")
    var futureAmount: BigDecimal = BigDecimal("0.00")

    @ApiModelProperty(value = "本月收入")
    var monthIncome: BigDecimal = BigDecimal("0.00")
}