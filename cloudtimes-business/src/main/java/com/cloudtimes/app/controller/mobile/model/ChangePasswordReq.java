package com.cloudtimes.app.controller.mobile.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "ChangePasswordReq", description = "修改密码请求参数")
@Data
@Slf4j
public class ChangePasswordReq {
    @ApiModelProperty(value = "新密码", required = true)
    private String passwordNew;
    @ApiModelProperty(value = "旧密码", required = true)
    private String passwordOld;
}
