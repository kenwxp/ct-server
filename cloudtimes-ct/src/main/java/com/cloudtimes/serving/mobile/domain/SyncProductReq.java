package com.cloudtimes.serving.mobile.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@ApiModel(description = "请求参数")
@Data
@Slf4j
public class SyncProductReq {
    @ApiModelProperty(value = "门店id", required = true)
    private String shopId;
}
