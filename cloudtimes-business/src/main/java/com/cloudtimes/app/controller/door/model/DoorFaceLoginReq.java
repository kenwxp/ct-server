package com.cloudtimes.app.controller.door.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Schema(description = "收银设备登录请求体")
@Data
@Slf4j
public class DoorFaceLoginReq {

    @Schema(description = "设备序列号", required = true)
    private String deviceSerial;

    @Schema(description = "门店编号")
    private String shopNo;

    @Schema(description = "设备名")
    private String deviceName;
}
