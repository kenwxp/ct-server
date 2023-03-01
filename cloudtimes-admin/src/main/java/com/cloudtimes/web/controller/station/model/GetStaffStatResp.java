package com.cloudtimes.web.controller.station.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@ApiModel(description = "返回参数")
@Data
@Slf4j
public class GetStaffStatResp {
    @ApiModelProperty("当日任务数")
    private String dayTaskCount;
    @ApiModelProperty("当日订单数")
    private String dayOrderCount;

}
