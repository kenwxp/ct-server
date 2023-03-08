package com.cloudtimes.serving.cash.service.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@ApiModel(description = "返回参数")
public class CashLoginResp {
    @ApiModelProperty("设备编号")
    private String deviceId;
    @ApiModelProperty("登录类型 0-全部支持 1-仅支持扫码 2-仅支持刷脸")
    private String loginType; // 联系人手机
    @ApiModelProperty("后台登录token")
    private String accessToken;
    @ApiModelProperty("是否值守 0-否 1-是")
    private String isSupervise;
    @ApiModelProperty("动态码")
    private String dynamicQrCode;
    @ApiModelProperty("门店号")
    private String shopNo;       // 门店号
    @ApiModelProperty("门店名")
    private String shopName;     // 门店名
    @ApiModelProperty("联系人姓名")
    private String contactName;  // 联系人姓名
    @ApiModelProperty("联系人手机")
    private String contactPhone; // 联系人手机

}