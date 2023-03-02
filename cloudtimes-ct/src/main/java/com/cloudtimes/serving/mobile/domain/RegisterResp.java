package com.cloudtimes.serving.mobile.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@ApiModel(description = "返回参数")
@Data
@Slf4j
public class RegisterResp {
    @ApiModelProperty("token")
    private String token;
}
