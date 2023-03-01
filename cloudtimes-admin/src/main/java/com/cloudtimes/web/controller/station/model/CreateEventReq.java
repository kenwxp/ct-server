package com.cloudtimes.web.controller.station.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ApiModel(description = "返回参数")
@Data
@Slf4j
public class CreateEventReq {
    @ApiModelProperty(value = "任务编号", required = true)
    private String taskId; // 任务编号
    @ApiModelProperty(value = "订单编号", notes = "订单异常时必填")
    private String orderId; // 订单编号
    @ApiModelProperty(value = "事件说明", required = true)
    private String remark; // 事件说明
}
