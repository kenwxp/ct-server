package com.cloudtimes.agent.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

@Schema(description = "代理门店收益统计")
class AgentStoreProfitStats {
    @Schema(description = "待结算分润")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    var futureDividend: BigDecimal = BigDecimal("0.00")

    @Schema(description = "已结算分润")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    var historyDividend: BigDecimal = BigDecimal("0.00")

    @Schema(description = "已结算佣金")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    var futureCommission: BigDecimal = BigDecimal("0.00")

    @Schema(description = "已结算佣金")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    var historyCommission: BigDecimal = BigDecimal("0.00")
}
