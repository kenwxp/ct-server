package com.cloudtimes.app.controller.mobile.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@ApiModel(description = "返回参数")
@Data
@Slf4j
public class GetShopListResp {
    @ApiModelProperty("token")
    private String token;
    @ApiModelProperty("店id")
    private String shopId;
    @ApiModelProperty("门店号")
    private String shopNo;
    @ApiModelProperty("店名")
    private String shopName;
    @ApiModelProperty("短地址")
    private String shortAddress;
    @ApiModelProperty("地区名")
    private String regionName;
    @ApiModelProperty("是否云值守")
    private String isSupervise;
    @ApiModelProperty("在线设备数")
    private String onLineDeviceNum;
    @ApiModelProperty("离线设备数")
    private String offLineDeviceNum;
}
