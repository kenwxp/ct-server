package com.cloudtimes.app.controller.cash.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "GetOrderIdReq", description = "刷脸token获取单号返回体")
@Data
@Slf4j
public class GetOrderIdReq {
    @ApiModelProperty(value = "人脸识别token", required = true)
    private String token;

}
