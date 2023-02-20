package com.cloudtimes.app.controller.cash.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "GetVoiceTokenResp", description = "获取商品列表返回列表项")
@Data
@Slf4j
public class GetVoiceTokenResp {
    @ApiModelProperty(value = "appid", required = true)
    private String appId;
    @ApiModelProperty(value = "语音token", required = true)
    private String voiceToken;
    @ApiModelProperty(value = "频道名", required = true)
    private String channelName;
    @ApiModelProperty(value = "用户uid", required = true)
    private int uid;
}
