package com.cloudtimes.agent.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Schema(description = "门店订单详情")
class StoreOrderDetail {
    @Schema(description = "订单编号")
    var id: String? = null

    @Schema(description = "实付金额")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    var actualAmount: BigDecimal? = null

    @Schema(description = "订单日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    var tranDate: LocalDate? = null

    @Schema(description = "支付方式")
    var paymentMode: String? = null

    @Schema(description = "订单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var createTime: LocalDateTime? = null
}
