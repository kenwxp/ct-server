package com.cloudtimes.serving.cash.service.domain;

import lombok.Data;

@Data
public class VoiceTokenData {
    private String appId;
    private String voiceToken;
    private String channelName;
    private int uid;
}
