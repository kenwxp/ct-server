package com.cloudtimes.app.controller.wechat.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 小程序登录校验请求体
 */
@ApiModel(value = "LoginCheckReq", description = "小程序登录校验请求体")
public class LoginCheckReq {

    @ApiModelProperty(value = "微信登录code", required = true)
    private String loginCode;

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }
}
