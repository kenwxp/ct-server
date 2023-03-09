package com.cloudtimes.serving.wechat.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 小程序登录校验请求体
 */
@ApiModel(description = "请求参数")
@Data
public class MpLoginCheckReq {
    @NotEmpty
    @ApiModelProperty(value = "微信登录code", required = true)
    private String loginCode;
}
