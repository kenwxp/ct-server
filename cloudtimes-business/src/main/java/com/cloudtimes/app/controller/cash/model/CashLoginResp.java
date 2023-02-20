package com.cloudtimes.app.controller.cash.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@ApiModel(value = "CashLoginResp", description = "小程序登录校验接口返回体")
public class CashLoginResp {
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
