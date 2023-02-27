package com.cloudtimes.agent.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.math.BigDecimal
import java.time.LocalDate

@ApiModel(value = "OrderMonthlyStats", description = "订单月统计")
class OrderMonthlyStats {
    @ApiModelProperty(value = "交易日期")
    var tranDate: LocalDate? = null

    @ApiModelProperty(value = "总金额")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    var totalAmount: BigDecimal? = null

    @ApiModelProperty(value = "总笔数")
    var totalCount: Long? = null
}