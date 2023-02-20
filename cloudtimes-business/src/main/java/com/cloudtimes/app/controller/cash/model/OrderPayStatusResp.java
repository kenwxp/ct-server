package com.cloudtimes.app.controller.cash.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "OrderPayStatusResp", description = "查询订单支付状态请求体")
@Data
@Slf4j
public class OrderPayStatusResp {
    @ApiModelProperty(value = "支付状态 0-待支付 1-支付中 2-支付成功 -1-支付失败", required = true)
    private String status;
}
