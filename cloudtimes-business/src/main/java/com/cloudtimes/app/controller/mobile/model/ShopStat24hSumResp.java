package com.cloudtimes.app.controller.mobile.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ApiModel(description = "返回参数")
@Data
@Slf4j
public class ShopStat24hSumResp {
    @ApiModelProperty("访问量")
    private String visit;
    @ApiModelProperty("营收额")
    private String income;
}
