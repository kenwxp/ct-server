package com.cloudtimes.web.controller.station.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ApiModel(description = "返回参数")
@Data
@Slf4j
public class JoinAudioReq {
    @ApiModelProperty(value = "门店编号", required = true)
    private String shopId;
    @ApiModelProperty(value = "是否加入 0-离开 1-加入", required = true)
    private String isJoin;
}
