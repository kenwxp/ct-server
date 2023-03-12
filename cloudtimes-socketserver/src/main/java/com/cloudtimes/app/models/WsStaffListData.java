package com.cloudtimes.app.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "WsStaffListData")
public class WsStaffListData {
    @Schema(description = "客服id")
    private String staffId;
    @Schema(description = "客服名")
    private String staffName;
    @Schema(description = "当前任务量")
    private String currentTaskCount;
    @Schema(description = "超额任务量")
    private String overflowTaskCount;
    @Schema(description = "超时任务量")
    private String overdueTaskCount;
    @Schema(description = "当前门店数")
    private String storeCount;
    @Schema(description = "当前视频数")
    private String videoCount;
    @Schema(description = "当前订单量")
    private String currentOrderCount;
    @Schema(description = "进行中订单量")
    private String inProgressOrderCount;
    @Schema(description = "未处理订单量")
    private String unHandleOrderCount;
    @Schema(description = "接单状态 0-开始接单 1-暂停接单 2-结束接单")
    private String acceptState;
}
