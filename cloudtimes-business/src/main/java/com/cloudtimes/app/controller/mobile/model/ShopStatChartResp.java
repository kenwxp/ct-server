package com.cloudtimes.app.controller.mobile.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ApiModel(description = "返回参数")
@Data
@Slf4j
public class ShopStatChartResp {
    @ApiModelProperty("标签")
    private String label;
    @ApiModelProperty("值")
    private String value;
}
