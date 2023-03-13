package com.cloudtimes.business.mobile.service;

import com.cloudtimes.account.domain.CtUser;
import com.cloudtimes.business.mobile.domain.LoginReq;
import com.cloudtimes.business.mobile.domain.LoginResp;
import com.cloudtimes.business.mobile.domain.RegisterReq;

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
     * @param param
     * @return
     */
    public CtUser shopBossRegister(RegisterReq param);

    /**
     * 用户登录
     *
     * @param param    登录参数
     * @param loginIp  登录ip
     * @return CtUser
     */
    public LoginResp shopBossLogin(LoginReq param, String loginIp);


}
