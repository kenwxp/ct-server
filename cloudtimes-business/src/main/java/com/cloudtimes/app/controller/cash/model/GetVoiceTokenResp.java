package com.cloudtimes.app.controller.cash.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "GetProductListResp", description = "获取商品列表返回列表项")
@Data
@Slf4j
public class GetVoiceTokenResp {
    private String appId;
    private String voiceToken;
    private String channelName;
    private int uid;
}
