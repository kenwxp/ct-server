package com.cloudtimes.serving.cash.service.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;


@ApiModel(description = "请求参数")
@Data
@Slf4j
public class CashLoginReq {
    @NotEmpty
    @ApiModelProperty(value = "设备序列号", required = true)
    private String deviceSerial;

    @ApiModelProperty(value = "门店编号")
    private String shopNo;

    @ApiModelProperty(value = "设备名")
    private String deviceName;

    @ApiModelProperty(value = "登录类型 0-全部支持 1-仅支持扫码 2-仅支持刷脸")
    private String loginType;
}
