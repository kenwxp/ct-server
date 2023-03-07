package com.cloudtimes.serving.mobile.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel(description = "请求参数")
@Data
@Slf4j
public class ShopStatSumReq {
    @ApiModelProperty(value = "门店id 门店查询类型为1时，必输", required = true)
    private String shopId;
    @NotEmpty
    @ApiModelProperty(value = "门店查询类型（0-全部门店，1-具体门店）", required = true)
    private String searchType;
    @NotEmpty
    @ApiModelProperty(value = "时间段类型（1-今天，2-昨天，3-本周，4-本月）", required = true)
    private String periodType;
}
