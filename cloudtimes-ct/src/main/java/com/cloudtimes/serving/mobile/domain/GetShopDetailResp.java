package com.cloudtimes.serving.mobile.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@ApiModel(description = "返回参数")
@Data
public class GetShopDetailResp {
    @ApiModelProperty("门店号")
    private String shopNo;
    @ApiModelProperty("门店名")
    private String shopName;
    @ApiModelProperty("店铺详细地址")
    private String address;
    @ApiModelProperty("联系人姓名")
    private String contactName;
    @ApiModelProperty("联系方式")
    private String contactPhone;
//    @ApiModelProperty("合同扫描图片地址")
//    private String contract;
//    @ApiModelProperty("合同开始时间")
//    private String contractBeginTime;
//    @ApiModelProperty("合同结束时间")
//    private String contractEndTime;
    @ApiModelProperty("店铺状态（0-正常 1-异常）")
    private String state;
    @ApiModelProperty("云值守状态（0-否 1-是）")
    private String isSupervise;
}
