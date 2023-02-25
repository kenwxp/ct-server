package com.cloudtimes.app.controller.mobile.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@ApiModel(description = "请求参数")
@Data
@Slf4j
public class GetShopListReq {
    @ApiModelProperty(value = "店名")
    private String shopName; // 店名
    @ApiModelProperty(value = "是否云值守 0-否 1-是")
    private String isSupervise;      // 是否云值守
}
