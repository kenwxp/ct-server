package com.cloudtimes.app.controller.wechat.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 小程序登录校验接口返回体
 */
@ApiModel(value = "LoginCheckResp", description = "小程序登录校验接口返回体")
public class LoginCheckResp {
    /**
     * 是否新用户 0-否 1-是
     */
    @ApiModelProperty("是否新用户 0-否 1-是")
    private String isNew;

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }
}
