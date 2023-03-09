package com.cloudtimes.serving.wechat.service;

import com.cloudtimes.serving.wechat.domain.ScanCodeReq;
import com.cloudtimes.serving.wechat.domain.ScanCodeResp;

import java.util.Map;

/**
 * 小程序业务相关Service业务层处理
 *
 * @author wangxp
 * @date 2023-02-07
 */
public interface ICtCustomerBusinessService {
    /**
     * @return map
     * shoppingId
     * isSupervise
     */
    public ScanCodeResp scanCode(String userId, ScanCodeReq param);
}
