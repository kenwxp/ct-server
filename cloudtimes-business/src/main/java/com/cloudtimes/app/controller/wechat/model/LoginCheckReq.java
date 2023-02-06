package com.cloudtimes.app.controller.wechat.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 小程序登录校验请求体
 */
@ApiModel(value = "LoginCheckReq", description = "小程序登录校验请求体")
@Data
@Slf4j
public class LoginCheckReq {

    @ApiModelProperty(value = "微信登录code", required = true)
    private String loginCode;

}
