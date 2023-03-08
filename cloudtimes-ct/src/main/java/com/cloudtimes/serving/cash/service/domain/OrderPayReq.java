package com.cloudtimes.serving.cash.service.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel(description = "请求参数")
@Data
@Slf4j
public class OrderPayReq {
    @NotNull
    @ApiModelProperty(value = "支付方式 0-扫码支付 1-刷脸支付", required = true)
    private int payType;
    @NotEmpty
    @ApiModelProperty(value = "支付码（扫码支付时，为动态码，刷脸支付时为face_code）", required = true)
    private String payCode;
    @NotEmpty
    @ApiModelProperty(value = "订单序列号", required = true)
    private String orderId;
    @NotNull
    @ApiModelProperty(value = "总计预付金额，单位分", required = true)
    private int totalAmount;
    @NotNull
    @ApiModelProperty(value = "物件总数", required = true)
    private int totalNum;
}
