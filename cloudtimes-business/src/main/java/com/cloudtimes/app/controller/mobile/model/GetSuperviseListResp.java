package com.cloudtimes.app.controller.mobile.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(description = "返回参数")
@Data
public class GetSuperviseListResp {
    @ApiModelProperty("值守流水号")
    private String superviseId;
    @ApiModelProperty("门店id")
    private String shopId;
    @ApiModelProperty("门店名")
    private String shopName;
    @ApiModelProperty("门店地址")
    private String shopAddress;
    @ApiModelProperty("值守状态 0-值守中 1-已结束")
    private String state;
    @ApiModelProperty("开始时间")
    private String beginTime;
    @ApiModelProperty("结束时间")
    private String endTime;
}
