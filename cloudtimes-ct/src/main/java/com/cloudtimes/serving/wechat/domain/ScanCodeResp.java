package com.cloudtimes.serving.wechat.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(description = "返回参数")
@Data
public class ScanCodeResp {
    @ApiModelProperty(value = "购物流水号")
    private String shoppingId;
    @ApiModelProperty(value = "是否云值守 0-否 1-是")
    private String isSupervise;
}
