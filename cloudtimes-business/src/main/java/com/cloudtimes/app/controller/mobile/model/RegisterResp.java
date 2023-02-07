package com.cloudtimes.app.controller.mobile.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@ApiModel(value = "LoginResp", description = "小程序登录校验接口返回体")
@Data
@Slf4j
public class RegisterResp {
    @ApiModelProperty("token")
    private String token;
}
