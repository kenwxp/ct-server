package com.cloudtimes.app.controller.cash.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@ApiModel(description = "请求参数")
@Data
@Slf4j
public class CashLoginReq {

    @ApiModelProperty(value = "设备序列号", required = true)
    private String deviceSerial;

    @ApiModelProperty(value = "门店编号")
    private String shopNo;

    @ApiModelProperty(value = "设备名")
    private String deviceName;
}
