package com.cloudtimes.serving.mobile.service;

import com.cloudtimes.account.domain.CtUser;

/**
 * 小程序用户登录Service业务层处理
 *
 * @author wangxp
 * @date 2023-02-02
 */
public interface ICtShopBossLoginService {

    /**
     * 用户注册
     *
     * @param phone
     * @param password
     * @return
     */
    public CtUser shopBossRegister(String phone, String password, String account, String nickName);

    /**
     * 用户登录
     *
     * @param phone    手机号
     * @param password 密码
     * @param loginIp  登录ip
     * @return CtUser
     */
    public CtUser shopBossLogin(String phone, String password, String loginIp);

    /**
     * 修改密码
     *
     * @param userId      用户id
     * @param newPassword 新密码
     * @param oldPassword 旧密码
     * @return
     */
    public boolean changePassword(String userId, String newPassword, String oldPassword);

}
