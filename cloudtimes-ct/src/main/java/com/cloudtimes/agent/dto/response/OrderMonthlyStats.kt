package com.cloudtimes.agent.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import java.time.LocalDate

@Schema(description = "订单月统计")
class OrderMonthlyStats {
    @Schema(description = "交易日期")
    var tranDate: LocalDate? = null

    @Schema(description = "总金额")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    var totalAmount: BigDecimal? = null

    @Schema(description = "总笔数")
    var totalCount: Long? = null
}
