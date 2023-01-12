package com.cloudtimes.system.service;

import com.cloudtimes.common.core.domain.model.AppLoginUser;
import com.cloudtimes.common.core.domain.model.LoginUser;
import com.cloudtimes.system.domain.SysMemberOnline;
import com.cloudtimes.system.domain.SysUserOnline;

/**
 * 在线会员 服务层
 * 
 * @author tank
 */
public interface ISysMemberOnlineService
{
    /**
     * 通过登录地址查询信息
     * 
     * @param ipaddr 登录地址
     * @param user 用户信息
     * @return 在线用户信息
     */
    public SysMemberOnline selectOnlineByIpaddr(String ipaddr, AppLoginUser user);

    /**
     * 通过用户名称查询信息
     * 
     * @param userName 用户名称
     * @param user 用户信息
     * @return 在线用户信息
     */
    public SysMemberOnline selectOnlineByUserName(String userName, AppLoginUser user);

    /**
     * 通过登录地址/用户名称查询信息
     * 
     * @param ipaddr 登录地址
     * @param userName 用户名称
     * @param user 用户信息
     * @return 在线用户信息
     */
    public SysMemberOnline selectOnlineByInfo(String ipaddr, String userName, AppLoginUser user);

    /**
     * 设置在线用户信息
     * 
     * @param user 用户信息
     * @return 在线用户
     */
    public SysMemberOnline loginUserToUserOnline(AppLoginUser user);
}
