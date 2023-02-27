package com.cloudtimes.agent.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@ApiModel(value = "StoreOrderDetail", description = "门店订单详情")
class StoreOrderDetail {
    @ApiModelProperty(value = "订单编号")
    var id: String? = null

    @ApiModelProperty(value = "实付金额")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    var actualAmount: BigDecimal? = null

    @ApiModelProperty(value = "订单日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    var tranDate: LocalDate? = null

    @ApiModelProperty(value = "支付方式")
    var paymentMode: String? = null

    @ApiModelProperty(value = "订单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var createTime: LocalDateTime? = null
}