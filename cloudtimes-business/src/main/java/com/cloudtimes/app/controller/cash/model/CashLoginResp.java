package com.cloudtimes.app.controller.cash.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;



@Data
@Slf4j
@ApiModel(value = "CashLoginResp", description = "小程序登录校验接口返回体")
public class CashLoginResp {
    @ApiModelProperty("后台登录token")
    private String accessToken;
}
