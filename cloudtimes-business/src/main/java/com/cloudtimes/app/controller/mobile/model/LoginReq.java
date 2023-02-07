package com.cloudtimes.app.controller.mobile.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@ApiModel(value = "LoginReq", description = "店家app登录请求体")
@Data
@Slf4j
public class LoginReq {

    @ApiModelProperty(value = "手机号", required = true)
    private String phone;

    @ApiModelProperty(value = "登录密码", required = true)
    private String password;

}
