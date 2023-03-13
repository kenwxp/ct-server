package com.cloudtimes.business.cash.service.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;


@Schema(description = "请求参数")
@Data
@Slf4j
public class CashLoginReq {
    @NotEmpty
    @Schema(description = "设备序列号", required = true)
    private String deviceSerial;

    @Schema(description = "门店编号")
    private String shopNo;

    @Schema(description = "设备名")
    private String deviceName;

    @Schema(description = "登录类型 0-全部支持 1-仅支持扫码 2-仅支持刷脸")
    private String loginType;
}
