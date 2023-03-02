package com.cloudtimes.station.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ApiModel(description = "返回参数")
@Data
@Slf4j
public class AcceptTaskReq {
    @ApiModelProperty(value = "接单标志（0-开始接单 1-暂停接单）", required = true)
    private String option;
}
