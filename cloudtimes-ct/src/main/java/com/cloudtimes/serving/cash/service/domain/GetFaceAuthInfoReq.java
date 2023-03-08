package com.cloudtimes.serving.cash.service.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;

@ApiModel(description = "请求参数")
@Data
public class GetFaceAuthInfoReq {
    @NotEmpty
    @ApiModelProperty(value = "刷脸rawdata", required = true)
    private String rawdata;
    @NotEmpty
    @ApiModelProperty(value = "凭证类型 1-微信凭证 2-收钱吧凭证", required = true)
    private String authType;
}
