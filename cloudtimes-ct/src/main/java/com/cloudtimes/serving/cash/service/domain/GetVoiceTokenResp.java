package com.cloudtimes.serving.cash.service.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Schema(description = "返回参数")
@Data
@Slf4j
public class GetVoiceTokenResp {
    @Schema(description = "appid", required = true)
    private String appId;
    @Schema(description = "语音token", required = true)
    private String voiceToken;
    @Schema(description = "频道名", required = true)
    private String channelName;
    @Schema(description = "用户uid", required = true)
    private int uid;
}
