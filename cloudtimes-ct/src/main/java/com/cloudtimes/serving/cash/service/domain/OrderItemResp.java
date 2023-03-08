package com.cloudtimes.serving.cash.service.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApiModel(description = "返回参数")
@Data
@NoArgsConstructor
public class OrderItemResp {
    @ApiModelProperty("订单号")
    private String orderId;

    public OrderItemResp(String orderId) {
        this.orderId = orderId;
    }
}
