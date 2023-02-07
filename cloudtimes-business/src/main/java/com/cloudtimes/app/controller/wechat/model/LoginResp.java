package com.cloudtimes.app.controller.wechat.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;



@Data
@Slf4j
@ApiModel(value = "LoginResp", description = "小程序登录校验接口返回体")
public class LoginResp {
    @ApiModelProperty("后台登录token")
    private String accessToken;
}
