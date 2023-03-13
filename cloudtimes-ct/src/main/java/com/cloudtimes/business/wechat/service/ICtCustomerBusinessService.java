package com.cloudtimes.business.wechat.service;

import com.cloudtimes.business.wechat.domain.ScanCodeReq;
import com.cloudtimes.business.wechat.domain.ScanCodeResp;

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
