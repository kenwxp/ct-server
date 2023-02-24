package com.cloudtimes.app.controller.cash.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@ApiModel(description = "返回参数")
@Data
public class GetFaceAuthInfoResp {
    @ApiModelProperty(value = "刷脸凭证authInfo", required = true)
    private String authInfo;
}
