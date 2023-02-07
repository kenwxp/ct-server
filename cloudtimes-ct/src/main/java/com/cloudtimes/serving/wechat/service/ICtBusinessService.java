package com.cloudtimes.serving.wechat.service;

import java.util.Map;

/**
 * 小程序业务相关Service业务层处理
 *
 * @author wangxp
 * @date 2023-02-07
 */
public interface ICtBusinessService {
    /**
     * @param storeNo
     * @param dynamicCode
     * @param deviceId
     * @return map
     * shoppingId
     * isSupervice
     */
    public Map<String, String> scanCode(String userId, String storeNo, String dynamicCode, String deviceId);
}
