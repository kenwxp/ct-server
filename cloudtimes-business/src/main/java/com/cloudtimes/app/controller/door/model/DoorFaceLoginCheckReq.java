package com.cloudtimes.app.controller.door.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 设备登录校验请求体
 */
@ApiModel(value = "DoorFaceLoginCheckReq", description = "设备登录校验请求体")
@Data
@Slf4j
public class DoorFaceLoginCheckReq {

    @ApiModelProperty(value = "设备序列号", required = true)
    private String deviceSerial;

}
