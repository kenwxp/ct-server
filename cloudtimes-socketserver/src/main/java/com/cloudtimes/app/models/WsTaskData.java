package com.cloudtimes.app.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class WsTaskData {
    @Schema(description = "当前任务量")
    private String currentTaskCount;
    @Schema(description = "超额任务量")
    private String overflowTaskCount;
    @Schema(description = "超时任务量")
    private String overdueTaskCount;
    @Schema(description = "当前订单数")
    private String currentOrderCount;
    @Schema(description = "接单状态 0-接单中 1-暂停接单")
    private String acceptState;
    @Schema(description = "任务列表")
    private List<WsTaskListData> taskList;
}
