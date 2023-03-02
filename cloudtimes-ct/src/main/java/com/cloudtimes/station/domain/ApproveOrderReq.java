package com.cloudtimes.station.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ApiModel(description = "返回参数")
@Data
@Slf4j
public class ApproveOrderReq {
    @ApiModelProperty(value = "订单编号", required = true)
    private String orderId;
    @ApiModelProperty(value = "审核标志（1-通过 2-拒绝）", required = true)
    private String option;
    @ApiModelProperty(value = "备注", required = true)
    private String remark;
}
