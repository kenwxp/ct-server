package com.cloudtimes.app.controller.wechat.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 小程序登录请求体
 */
@ApiModel(value = "LoginReq", description = "小程序登录请求体")
@Data
@Slf4j
public class LoginReq {
    /**
     * 微信登录code
     */
    @ApiModelProperty(value = "微信登录code", required = true)
    private String loginCode;
    /**
     * 获取手机号code
     */
    @ApiModelProperty(value = "获取手机号code", required = true)
    private String phoneCode;

}
