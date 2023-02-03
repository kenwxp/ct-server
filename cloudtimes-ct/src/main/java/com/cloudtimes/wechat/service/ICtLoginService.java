package com.cloudtimes.wechat.service;

import java.util.Map;

/**
 * 小程序用户登录Service业务层处理
 *
 * @author wangxp
 * @date 2023-02-02
 */
public interface ICtLoginService {
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
     * @return map
     * id             用户id
     * moneyAmount    现金余额
     * scoreAmount    积分余额
     * creditScore    信用分
     */
    public Map<String, Object> customerLogin(String loginCode, String phoneCode, String loginIp);

}
