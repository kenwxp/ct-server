package com.cloudtimes.app.controller.cash.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@ApiModel(value = "GetFaceAuthInfoResp", description = "获取刷脸凭证请求体")
@Data
public class GetFaceAuthInfoResp {
    @ApiModelProperty(value = "刷脸凭证authInfo", required = true)
    private String authInfo;
}
