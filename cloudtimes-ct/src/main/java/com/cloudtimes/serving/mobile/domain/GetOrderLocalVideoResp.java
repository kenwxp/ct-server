package com.cloudtimes.serving.mobile.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@ApiModel(description = "返回参数")
@Data
public class GetOrderLocalVideoResp {
    @ApiModelProperty("视频播放url")
    private String url;
    @ApiModelProperty("视频播放token")
    private String token;
}
