package com.cloudtimes.serving.mobile.service;

/**
 * 小程序用户登录Service业务层处理
 *
 * @author wangxp
 * @date 2023-02-02
 */
public interface ICtShopBossBusinessService {
    /**
     * 申请或取消与值守方法
     *
     * @param userId
     * @param storeId
     * @param opFlag
     * @return
     */
    public boolean applySupervise(String userId, String storeId, String opFlag);
}
