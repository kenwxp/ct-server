package com.cloudtimes.app.controller.cash.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "OrderItemResp", description = "修改订单商品返回体")
@Data
@Slf4j
public class OrderItemResp {
    @ApiModelProperty("订单号")
    private String orderId;

    public OrderItemResp(String orderId) {
        this.orderId = orderId;
    }
}
