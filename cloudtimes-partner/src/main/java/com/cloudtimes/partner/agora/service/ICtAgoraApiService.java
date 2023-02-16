package com.cloudtimes.partner.agora.service;

import com.cloudtimes.partner.agora.core.RtcTokenBuilder2;

public interface ICtAgoraApiService {
    public String getAgoraToken(int intUid, String channelName, RtcTokenBuilder2.Role role);
}
