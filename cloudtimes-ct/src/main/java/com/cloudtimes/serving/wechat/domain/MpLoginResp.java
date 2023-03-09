package com.cloudtimes.serving.wechat.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "返回参数")
public class MpLoginResp {
    @ApiModelProperty("用户编号")
    private String userId;
    @ApiModelProperty("后台登录token")
    private String accessToken;

}
