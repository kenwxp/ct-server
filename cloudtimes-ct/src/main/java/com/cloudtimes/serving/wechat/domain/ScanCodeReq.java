package com.cloudtimes.serving.wechat.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@ApiModel(description = "请求参数")
@Data
public class ScanCodeReq {
    @NotEmpty
    @ApiModelProperty(value = "门店编号（非主键）", required = true)
    private String shopId;
    @ApiModelProperty(value = "动态码内容", required = true)
    private String dynamicCode;
    @ApiModelProperty(value = "设备id", required = true)
    private String did;
}
