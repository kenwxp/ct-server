package com.cloudtimes.serving.mobile.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@ApiModel(description = "请求参数")
@Data
@Slf4j
public class GetDeviceListReq {
    @NotEmpty
    @ApiModelProperty(value = "店名id")
    private String shopId;
    @NotNull
    @ApiModelProperty(value = "查询类型 0-全设备列表 1-播放链接列表")
    private int queryType;
}
