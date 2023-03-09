package com.cloudtimes.serving.wechat.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 小程序登录请求体
 */
@ApiModel(description = "请求参数")
@Data
public class MpLoginReq {
    /**
     * 微信登录code
     */
    @NotEmpty
    @ApiModelProperty(value = "微信登录code", required = true)
    private String loginCode;
    /**
     * 获取手机号code
     */
    @ApiModelProperty(value = "获取手机号code", required = true)
    private String phoneCode;

}
