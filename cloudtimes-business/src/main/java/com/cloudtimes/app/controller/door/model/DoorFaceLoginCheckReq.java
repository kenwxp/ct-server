package com.cloudtimes.app.controller.door.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 设备登录校验请求体
 */
@Schema(description = "设备登录校验请求体")
@Data
@Slf4j
public class DoorFaceLoginCheckReq {

    @Schema(description = "设备序列号", required = true)
    private String deviceSerial;

}
