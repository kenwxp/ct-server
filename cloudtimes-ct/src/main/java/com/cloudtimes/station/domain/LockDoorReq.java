package com.cloudtimes.station.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;

@Schema(description = "返回参数")
@Data
@Slf4j
public class LockDoorReq {
    @NotEmpty
    @Schema(description = "任务编号", required = true)
    private String taskId;
    @NotEmpty
    @Schema(description = "锁操作 0-解锁 1-锁", required = true)
    private String option;
}
