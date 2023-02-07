package com.cloudtimes.app.controller.mobile.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@ApiModel(value = "RegisterReq", description = "店家app注册请求体")
@Data
@Slf4j
public class RegisterReq {
    @ApiModelProperty(value = "手机号", required = true)
    private String phone;
    @ApiModelProperty(value = "登录密码", required = true)
    private String password;
    @ApiModelProperty(value = "账户名", required = true)
    private String account;
    @ApiModelProperty(value = "昵称", required = true)
    private String nickName;
}
