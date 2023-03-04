package com.cloudtimes.partner.weixin.impl;

import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.utils.http.HttpUtils;
import com.cloudtimes.common.utils.uuid.UUID;
import com.cloudtimes.partner.config.PartnerConfig;
import com.cloudtimes.partner.weixin.ICtWeixinOfficialApiService;
import com.xkcoding.http.config.HttpConfig;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.request.AuthWeChatMpRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * 微公众号
 */
@Service
public class CtWeixinOfficialApiServiceImpl implements ICtWeixinOfficialApiService {

    @Autowired
    private PartnerConfig partnerConfig;

    @Override
    public String getWXAuthURL(String type, String inviteCode) {
        AuthRequest authRequest = createAuthRequest(type, inviteCode);
        return authRequest.authorize(AuthStateUtils.createState());
    }

    private AuthRequest createAuthRequest(String type, String inviteCode) {
        String redirectUrl = partnerConfig.getLoginCallbackApiUrl();
        String params = "";
        if (type != null) params += "ty=" + type;
        if (inviteCode != null) {
            if (params.isEmpty()) {
                params += "ic=" + inviteCode;
            } else {
                params += "&ic=" + inviteCode;
            }
        }
        if (!params.isEmpty()) {
            redirectUrl += "?" + params;
        }
        HttpConfig httpConfig = new HttpConfig();
        httpConfig.setTimeout(60000);
        return new AuthWeChatMpRequest(AuthConfig.builder().httpConfig(httpConfig)
                .clientId(partnerConfig.getWxOfficialAppid())
                .clientSecret(partnerConfig.getWxOfficialSecret())
                .redirectUri(redirectUrl)
                .build());
    }

    @Override
    public AuthResponse login(AuthCallback callback) {
        AuthRequest authRequest = createAuthRequest(null, null);
        return authRequest.login(callback);
    }

    @Override
    public String getJSSDKSign(String accessToken) {
        String apiURL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken + "&type=jsapi";
        String result = HttpUtils.sendGet(apiURL);
        return result;
    }

    @Override
    public String getMPAccessToken() {
        String resreshToken = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + partnerConfig.getWxOfficialAppid() + "&secret=" + partnerConfig.getWxOfficialSecret();
        String result = HttpUtils.sendGet(resreshToken);
        return result;
    }

    @Override
    public AjaxResult getJSSDKSign(String accessToken, String ticket, String url) throws NoSuchAlgorithmException {
        //生成随机字符串  noncestr
        String nonceStr = UUID.randomUUID().toString();
        //时间戳 timestamp，精确到秒
        Long timestamp = System.currentTimeMillis() / 1000L;
        //url
        url = URLDecoder.decode(url);
        var Stitching = "jsapi_ticket=" + ticket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + url;
        //SHA1 加密
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        digest.update(Stitching.getBytes());
        byte messageDigest[] = digest.digest();

        StringBuffer hexStr = new StringBuffer();
        // 字节数组转换为 十六进制 数
        for (int i = 0; i < messageDigest.length; i++) {
            String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
            if (shaHex.length() < 2) {
                hexStr.append(0);
            }
            hexStr.append(shaHex);
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("appid", partnerConfig.getWxOfficialAppid());
        resultMap.put("timestamp", timestamp);
        resultMap.put("nonceStr", nonceStr);
        resultMap.put("signature", hexStr.toString());
        return AjaxResult.success(resultMap);
    }
}
