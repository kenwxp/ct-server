package com.cloudtimes.app.controller.cash.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 设备登录校验请求体
 */
@ApiModel(description = "请求参数")
@Data
@Slf4j
public class CashLoginCheckReq {

    @ApiModelProperty(value = "设备序列号", required = true)
    private String deviceSerial;

}
