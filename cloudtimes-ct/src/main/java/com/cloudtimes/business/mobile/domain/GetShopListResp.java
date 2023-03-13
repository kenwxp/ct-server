package com.cloudtimes.business.mobile.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Schema(description = "返回参数")
@Data
@Slf4j
public class GetShopListResp {
    @Schema(description = "店id")
    private String shopId;
    @Schema(description = "门店号")
    private String shopNo;
    @Schema(description = "店名")
    private String shopName;
    @Schema(description = "短地址")
    private String shortAddress;
    @Schema(description = "地区名")
    private String regionName;
    @Schema(description = "是否云值守")
    private String isSupervise;
    @Schema(description = "在线设备数")
    private String onLineDeviceNum;
    @Schema(description = "离线设备数")
    private String offLineDeviceNum;
}
