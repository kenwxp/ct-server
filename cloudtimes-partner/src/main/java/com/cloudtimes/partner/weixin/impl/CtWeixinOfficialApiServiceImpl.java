package com.cloudtimes.partner.weixin.impl;

import com.cloudtimes.partner.config.PartnerConfig;
import com.cloudtimes.partner.weixin.ICtWeixinOfficialApiService;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.request.AuthWeChatEnterpriseRequest;
import me.zhyd.oauth.request.AuthWeChatMpRequest;
import me.zhyd.oauth.request.AuthWeChatOpenRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 微公众号
 */
@Service
public class CtWeixinOfficialApiServiceImpl implements ICtWeixinOfficialApiService {

    @Autowired
    private PartnerConfig partnerConfig;

    @Override
    public String getWXAuthURL() {
        AuthRequest authRequest = createAuthRequest();
        return authRequest.authorize(AuthStateUtils.createState());
    }

    private AuthRequest createAuthRequest() {
        return new AuthWeChatMpRequest(AuthConfig.builder()
                .clientId(partnerConfig.getWxOfficialAppid())
                .clientSecret(partnerConfig.getWxOfficialSecret())
                .redirectUri(partnerConfig.getLoginCallbackApiUrl())
                .build());
    }

    @Override
    public AuthResponse login(AuthCallback callback) {
        AuthRequest authRequest = createAuthRequest();
        return authRequest.login(callback);
    }
}
