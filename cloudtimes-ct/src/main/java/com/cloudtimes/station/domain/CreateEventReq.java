package com.cloudtimes.station.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Schema(description = "返回参数")
@Data
@Slf4j
public class CreateEventReq {
    @Schema(description = "任务编号", required = true)
    private String taskId; // 任务编号
    @Schema(description = "事件说明", required = true)
    private String remark; // 事件说明
}
