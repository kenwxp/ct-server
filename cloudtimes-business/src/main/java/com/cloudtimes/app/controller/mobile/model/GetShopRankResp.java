package com.cloudtimes.app.controller.mobile.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(description = "返回参数")
@Data
public class GetShopRankResp {
    @ApiModelProperty("门店名")
    private String shopName;
    @ApiModelProperty("在店人数")
    private String visitNum;
}
