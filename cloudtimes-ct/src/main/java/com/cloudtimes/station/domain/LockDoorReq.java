package com.cloudtimes.station.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;

@ApiModel(description = "返回参数")
@Data
@Slf4j
public class LockDoorReq {
    @NotEmpty
    @ApiModelProperty(value = "任务编号", required = true)
    private String taskId;
    @NotEmpty
    @ApiModelProperty(value = "锁操作 0-解锁 1-锁", required = true)
    private String option;
}
