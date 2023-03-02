package com.cloudtimes.app.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class WsTaskData {
    @ApiModelProperty("当前任务量")
    private String currentTaskCount;
    @ApiModelProperty("超额任务量")
    private String overflowTaskCount;
    @ApiModelProperty("超时任务量")
    private String overdueTaskCount;
    @ApiModelProperty("当前订单数")
    private String currentOrderCount;
    @ApiModelProperty("接单状态 0-接单中 1-暂停接单")
    private String acceptStatus;
    @ApiModelProperty("任务列表")
    private List<WsTaskListData> taskList;
}
