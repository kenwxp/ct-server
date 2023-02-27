package com.cloudtimes.serving.mobile.service;

/**
 * 小程序用户登录Service业务层处理
 *
 * @author wangxp
 * @date 2023-02-02
 */
public interface ICtShopBossBusinessService {
    /**
     * 修改密码
     *
     * @param userId      用户id
     * @param newPassword 新密码
     * @param oldPassword 旧密码
     * @return
     */
    public boolean changePassword(String userId, String newPassword, String oldPassword);
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
