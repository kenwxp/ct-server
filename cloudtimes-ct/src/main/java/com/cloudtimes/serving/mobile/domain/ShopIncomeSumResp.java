package com.cloudtimes.serving.mobile.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ApiModel(description = "返回参数")
@Data
@Slf4j
public class ShopIncomeSumResp {
    @ApiModelProperty("总营收 数字到分")
    private String totalIncome;
    @ApiModelProperty("付款单数 付款单数")
    private String payCount;
    @ApiModelProperty("客单价 客单价，数据到分")
    private String payPerCustomer;
    @ApiModelProperty("较上期 单位%")
    private String diffRate;
}
