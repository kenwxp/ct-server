package com.cloudtimes.app.controller.wechat.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "ScanCodeReq", description = "扫动态码返回")
@Data
@Slf4j
public class ScanCodeResp {
    @ApiModelProperty(value = "购物流水号")
    private String shoppingId;
    @ApiModelProperty(value = "是否云值守 0-否 1-是")
    private String isSupervise;
}
