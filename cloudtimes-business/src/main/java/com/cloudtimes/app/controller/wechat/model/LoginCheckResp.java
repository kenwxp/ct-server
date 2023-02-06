package com.cloudtimes.app.controller.wechat.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 小程序登录校验接口返回体
 */
@ApiModel(value = "LoginCheckResp", description = "小程序登录校验接口返回体")
@Data
@Slf4j
public class LoginCheckResp {
    /**
     * 是否新用户 0-否 1-是
     */
    @ApiModelProperty("是否新用户 0-否 1-是")
    private String isNew;

}
