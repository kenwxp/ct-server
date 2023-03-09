package com.cloudtimes.hardwaredevice.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;

@ApiModel(description = "请求参数")
@Data
@Slf4j
public class DeviceOnlineReq {
    @NotEmpty
    @ApiModelProperty(value = "设备编号", required = true)
    private String deviceId;
    @NotEmpty
    @ApiModelProperty(value = "操作 0-下云 1-上云", required = true)
    private String option;
}
