package com.cloudtimes.web.controller.station.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ApiModel(description = "返回参数")
@Data
@Slf4j
public class AcceptTaskReq {
    @ApiModelProperty(value = "是否接单（0-否 1-是）", required = true)
    private String option;
}
