package com.cloudtimes.app.controller.mobile.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@ApiModel(description = "请求参数")
@Data
@Slf4j
public class GetDeviceListReq {
    @ApiModelProperty(value = "店名id")
    private String shopId;
    @ApiModelProperty(value = "查询类型 0-全设备列表 1-播放链接列表")
    private int queryType;
}
