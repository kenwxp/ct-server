package com.cloudtimes.partner.weixin;

import com.cloudtimes.partner.weixin.domain.WxpayfaceAuthInfoResp;

/**
 * 微信刷脸相关api
 */
public interface ICtWeixinFaceApiService {
    /**
     * 获取设备刷脸凭证
     *
     * @param storeId
     * @param storeName
     * @param deviceId
     * @param rawData
     * @return
     */
    public WxpayfaceAuthInfoResp getWxpayfaceAuthInfo(String storeId, String storeName, String deviceId, String rawData);


    /**
     * 根据刷脸token 获取unionId
     *
     * @param token
     * @return
     */
    public String getWxpayfaceUserUnionId(String token);
}
