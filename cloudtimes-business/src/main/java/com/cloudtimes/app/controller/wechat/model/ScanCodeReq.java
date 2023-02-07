package com.cloudtimes.app.controller.wechat.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "ScanCodeReq", description = "扫动态码请求体")
@Data
@Slf4j
public class ScanCodeReq {
    @ApiModelProperty(value = "门店编号（非主键）", required = true)
    private String shopId;
    @ApiModelProperty(value = "动态码内容", required = true)
    private String dynamicCode;
    @ApiModelProperty(value = "设备id", required = true)
    private String did;
}
