package com.cloudtimes.partner.weixin;

import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;

import java.util.Map;

/**
 * 微公众号
 */
public interface ICtWeixinOfficialApiService {
    /**
     * 获取微信授权地址
     *
     * @return
     */
    public String getWXAuthURL();

    public AuthResponse<AuthUser> login(AuthCallback callback);


}
