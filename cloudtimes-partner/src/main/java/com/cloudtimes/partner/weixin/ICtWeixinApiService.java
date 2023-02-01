package com.cloudtimes.partner.weixin;

import java.util.Map;

public interface ICtWeixinApiService {
    /**
     * 获取微信access token
     *
     * @return
     */
    public String getAccessToken();

    /**
     * 获取用户openid
     *
     * @param jsCode 微信登录组件生成
     * @return map
     * errcode     int64  // 错误码
     * errmsg      string // 错误信息
     * openid      string // 用户唯一标识
     * session_key string // 会话密钥
     * unionid     string // 用户在开放平台的唯一标识符，若当前小程序已绑定到微信开放平台帐号下会返回，详见 UnionID 机制说明。
     */
    public Map<String, String> getUserSession(String jsCode);

    /**
     * 获取用户手机号
     *
     * @param jsCode 微信获取手机号组件生成
     * @return map
     * errcode     int64  // 错误码
     * errmsg      string // 错误信息
     * phone_info  map
     * * phoneNumber     string  // 用户绑定的手机号（国外手机号会有区号）
     * * purePhoneNumber string  // 没有区号的手机号
     * * countryCode     string  // 区号
     */
    public Map<String, String> getUserPhoneInfo(String jsCode);


}
