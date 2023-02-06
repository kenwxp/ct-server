package com.cloudtimes.serving.wechat.service;

import com.cloudtimes.account.domain.CtUser;

/**
 * 小程序用户登录Service业务层处理
 *
 * @author wangxp
 * @date 2023-02-02
 */
public interface ICtCustomerLoginService {
    /**
     * 根据微信loginCode校验是否为新用户
     *
     * @param loginCode
     * @return
     */
    public boolean checkCustomerNew(String loginCode);

    /**
     * 用户登录
     *
     * @param loginCode
     * @param phoneCode
     * @param loginIp   登录ip
     * @return CtUser
     */
    public CtUser customerLogin(String loginCode, String phoneCode, String loginIp);

}
