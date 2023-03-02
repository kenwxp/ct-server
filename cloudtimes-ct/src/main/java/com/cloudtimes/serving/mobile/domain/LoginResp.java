package com.cloudtimes.serving.mobile.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@ApiModel(description = "返回参数")
@Data
@Slf4j
public class LoginResp {
    @ApiModelProperty("token")
    private String token;
    @ApiModelProperty("用户编号")
    private String id;
    @ApiModelProperty("用户名")
    private String name;
    @ApiModelProperty("手机号")
    private String phone;
}
