package com.cloudtimes.app.controller.cash.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "GetWxFaceAuthInfoReq", description = "获取刷脸凭证请求体")
@Data
@Slf4j
public class GetFaceAuthInfoReq {
    @ApiModelProperty(value = "刷脸rawdata", required = true)
    private String rawdata;
    @ApiModelProperty(value = "凭证类型 1-微信凭证 2-收钱吧凭证", required = true)
    private String authType;
}
