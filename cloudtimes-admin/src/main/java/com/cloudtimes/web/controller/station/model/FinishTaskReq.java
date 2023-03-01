package com.cloudtimes.web.controller.station.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ApiModel(description = "返回参数")
@Data
@Slf4j
public class FinishTaskReq {
    @ApiModelProperty(value = "任务编号", required = true)
    private String taskId;
    @ApiModelProperty(value = "批复", required = true)
    private String remark;
}
