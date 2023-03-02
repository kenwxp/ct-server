package com.cloudtimes.serving.mobile.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ApiModel(description = "返回参数")
@Data
@Slf4j
public class ShopVisitSumResp {
    @ApiModelProperty("总客流")
    private String totalVisit;
    @ApiModelProperty("付款客流")
    private String payVisit;
    @ApiModelProperty("进入频率")
    private String visitFrequency;
    @ApiModelProperty("较上期 单位% ")
    private String diffRate;
}
