package com.cloudtimes.app.controller.cash.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "GetOrderIdResp", description = "刷脸token获取单号返回体")
@Data
@Slf4j
public class GetOrderIdResp {
    @ApiModelProperty(value = "新单号", required = true)
    private String orderId;
    @ApiModelProperty(value = "顾客手机号", required = true)
    private String customerPhone;
}
