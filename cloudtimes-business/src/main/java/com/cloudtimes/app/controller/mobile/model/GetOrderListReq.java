package com.cloudtimes.app.controller.mobile.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@ApiModel(description = "请求参数")
@Data
@Slf4j
public class GetOrderListReq {
    @ApiModelProperty(value = "页码", required = true)
    private int pageNum;
    @ApiModelProperty(value = "每页条数", required = true)
    private int pageSize;
    @ApiModelProperty(value = "门店id", required = true)
    private String shopId;
    @ApiModelProperty(value = "支付类型 0-支付宝 1-微信 2-网银 空-全部 ")
    private String payType;
    @ApiModelProperty(value = "订单类型 1-值守 2-非值守 空-全部")
    private String orderType;
    @ApiModelProperty(value = "时间段类型（0-自定义 1-今天，2-昨天，3-本周，4-本月", required = true)
    private String periodType;
    @ApiModelProperty(value = "开始时间 periodType=0时 必填 格式：yyyy-mm-dd")
    private String beginTime;
    @ApiModelProperty(value = "结束时间 periodType=0时 必填 格式：yyyy-mm-dd")
    private String endTime;
}
