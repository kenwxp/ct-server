package com.cloudtimes.app.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "WsStaffListData")
public class WsStaffListData {
    @ApiModelProperty("客服id")
    private String staffId;
    @ApiModelProperty("客服名")
    private String staffName;
    @ApiModelProperty("当前任务量")
    private String currentTaskCount;
    @ApiModelProperty("超额任务量")
    private String overflowTaskCount;
    @ApiModelProperty("超时任务量")
    private String overdueTaskCount;
    @ApiModelProperty("当前门店数")
    private String storeCount;
    @ApiModelProperty("当前视频数")
    private String videoCount;
    @ApiModelProperty("当前订单量")
    private String currentOrderCount;
    @ApiModelProperty("进行中订单量")
    private String inProgressOrderCount;
    @ApiModelProperty("未处理订单量")
    private String unHandleOrderCount;
}
