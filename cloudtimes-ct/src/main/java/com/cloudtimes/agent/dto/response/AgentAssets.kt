package com.cloudtimes.agent.dto.response

import com.cloudtimes.agent.domain.CtUserAgent
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

@Schema(description = "代理资产")
class AgentAssets: CtUserAgent() {
    @Schema(description = "待结算收入")
    var futureAmount: BigDecimal = BigDecimal("0.00")

    @Schema(description = "本月收入")
    var monthIncome: BigDecimal = BigDecimal("0.00")
}
