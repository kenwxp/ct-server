package com.cloudtimes.partner.agora.service.impl;

import com.cloudtimes.partner.agora.core.RtcTokenBuilder2;
import com.cloudtimes.partner.agora.service.ICtAgoraApiService;
import com.cloudtimes.partner.config.PartnerConfig;
import com.cloudtimes.partner.pay.shouqianba.service.impl.CtShouqianbaApiServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CtAgoraApiServiceImpl implements ICtAgoraApiService {
    private Logger log = LoggerFactory.getLogger(CtAgoraApiServiceImpl.class);
    @Autowired
    private PartnerConfig config;

    @Override
    public String getAgoraToken(int intUid, String channelName, RtcTokenBuilder2.Role role) {
        String appID = config.getAgoraAppId();
        String appCertificate = config.getAgoraCertificate();
        // AccessToken2 过期的时间，单位为秒
        // 当 AccessToken2 过期但权限未过期时，用户仍在频道里并且可以发流，不会触发 SDK 回调。
        // 但一旦用户和频道断开连接，用户将无法使用该 Token 加入同一频道。请确保 AccessToken2 的过期时间晚于权限过期时间。
        //tokenExpireTimeInSeconds := uint32(86400)
        int tokenExpireTimeInSeconds = 86400;
        // 权限过期的时间，单位为秒。
        // 权限过期30秒前会触发 token-privilege-will-expire 回调。
        // 权限过期时会触发 token-privilege-did-expire 回调。
        // 为作演示，在此将过期时间设为 40 秒。你可以看到客户端自动更新 Token 的过程
        //privilegeExpireTimeInSeconds := uint32(86000)
        int privilegeExpireTimeInSeconds = 86000;
        RtcTokenBuilder2 builder = new RtcTokenBuilder2();
        String token = builder.buildTokenWithUid(appID, appCertificate, channelName, intUid, role, tokenExpireTimeInSeconds, privilegeExpireTimeInSeconds);
        log.info("agora voice token:", intUid, channelName, token);
        return token;
    }
}
