package com.cloudtimes.hardwaredevice.domain;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;

@Schema(description = "请求参数")
@Data
@Slf4j
public class DeviceActivateReq {
    @NotEmpty
    @Schema(description = "设备编号", required = true)
    private String deviceId;
    @NotEmpty
    @Schema(description = "激活码", required = true)
    private String code;
}
