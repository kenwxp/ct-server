package com.cloudtimes.app.controller.mobile.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ApiModel(description = "请求参数")
@Data
@Slf4j
public class ShopStatChartReq {
    @ApiModelProperty(value = "门店id 门店查询类型为1时，必输", required = true)
    private String shopId;
    @ApiModelProperty(value = "门店查询类型（0-全部门店，1-具体门店）", required = true)
    private String searchType;
    @ApiModelProperty(value = "步幅类型（0-时，1-日，2-周，3-月）", required = true)
    private String stepType;
}
