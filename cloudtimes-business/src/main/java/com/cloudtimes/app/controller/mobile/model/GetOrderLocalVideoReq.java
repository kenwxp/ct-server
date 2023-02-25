package com.cloudtimes.app.controller.mobile.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@ApiModel(description = "请求参数")
@Data
@Slf4j
public class GetOrderLocalVideoReq {
    @ApiModelProperty(value = "订单编号", required = true)
    private String orderId;
    @ApiModelProperty(value = "设备序列号", required = true)
    private String deviceSerial;
}
