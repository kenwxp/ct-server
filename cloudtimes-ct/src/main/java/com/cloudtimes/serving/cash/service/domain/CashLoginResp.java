package com.cloudtimes.serving.cash.service.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Schema(description = "返回参数")
public class CashLoginResp {
    @Schema(description = "设备编号")
    private String deviceId;
    @Schema(description = "登录类型 0-全部支持 1-仅支持扫码 2-仅支持刷脸")
    private String loginType; // 联系人手机
    @Schema(description = "后台登录token")
    private String accessToken;
    @Schema(description = "是否值守 0-否 1-是")
    private String isSupervise;
    @Schema(description = "动态码")
    private String dynamicQrCode;
    @Schema(description = "门店号")
    private String shopNo;       // 门店号
    @Schema(description = "门店名")
    private String shopName;     // 门店名
    @Schema(description = "联系人姓名")
    private String contactName;  // 联系人姓名
    @Schema(description = "联系人手机")
    private String contactPhone; // 联系人手机

}
