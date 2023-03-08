package com.cloudtimes.serving.cash.service.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;

@ApiModel(description = "请求参数")
@Data
@Slf4j
public class GetOrderIdReq {
    @NotEmpty
    @ApiModelProperty(value = "人脸识别token", required = true)
    private String token;

}
