package com.cloudtimes.app.controller.cash.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "OrderPayReq", description = "订单支付请求体")
@Data
@Slf4j
public class OrderPayReq {
    @ApiModelProperty(value = "支付方式 1-扫码支付 2-刷脸支付", required = true)
    private int payType;
    @ApiModelProperty(value = "支付码（扫码支付时，为动态码，刷脸支付时为face_code）", required = true)
    private String payCode;
    @ApiModelProperty(value = "订单序列号", required = true)
    private String orderId;
    @ApiModelProperty(value = "总计预付金额，单位分", required = true)
    private int totalAmount;
    @ApiModelProperty(value = "物件总数", required = true)
    private int totalNum;
}
