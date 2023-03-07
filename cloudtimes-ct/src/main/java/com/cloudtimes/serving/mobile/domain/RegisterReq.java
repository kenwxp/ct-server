package com.cloudtimes.serving.mobile.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;


@ApiModel(description = "请求参数")
@Data
@Slf4j
public class RegisterReq {
    @NotEmpty
    @ApiModelProperty(value = "手机号", required = true)
    private String phone;
    @NotEmpty
    @ApiModelProperty(value = "登录密码", required = true)
    private String password;
    @NotEmpty
    @ApiModelProperty(value = "账户名", required = true)
    private String account;
    @NotEmpty
    @ApiModelProperty(value = "昵称", required = true)
    private String nickName;
}
