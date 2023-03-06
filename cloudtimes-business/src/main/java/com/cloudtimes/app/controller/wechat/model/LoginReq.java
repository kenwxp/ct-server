package com.cloudtimes.app.controller.wechat.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 小程序登录请求体
 */
@ApiModel(description = "请求参数")
@Data
@Slf4j
public class LoginReq {
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
