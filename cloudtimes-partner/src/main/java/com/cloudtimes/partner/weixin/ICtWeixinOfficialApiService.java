package com.cloudtimes.partner.weixin;

import com.cloudtimes.common.core.domain.AjaxResult;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;

import java.security.NoSuchAlgorithmException;
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

    public String getJSSDKSgin(String accessToken);


    public String getMPAccessToken();

    public AjaxResult getJSSDKSign(String accessToken,String ticket,String url) throws NoSuchAlgorithmException;
}
