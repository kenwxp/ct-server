package com.cloudtimes.system.service.impl;

import com.cloudtimes.common.core.domain.model.AppLoginUser;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.system.domain.SysMemberOnline;
import com.cloudtimes.system.service.ISysMemberOnlineService;
import org.springframework.stereotype.Service;

/**
 * 在线用户 服务层处理
 * 
 * @author tank
 */
@Service
public class SysMemberOnlineServiceImpl implements ISysMemberOnlineService
{
    /**
     * 通过登录地址查询信息
     * 
     * @param ipaddr 登录地址
     * @param user 用户信息
     * @return 在线用户信息
     */
    @Override
    public SysMemberOnline selectOnlineByIpaddr(String ipaddr, AppLoginUser user)
    {
        if (StringUtils.equals(ipaddr, user.getIpaddr()))
        {
            return loginUserToUserOnline(user);
        }
        return null;
    }

    /**
     * 通过用户名称查询信息
     * 
     * @param userName 用户名称
     * @param user 用户信息
     * @return 在线用户信息
     */
    @Override
    public SysMemberOnline selectOnlineByUserName(String userName, AppLoginUser user)
    {
        if (StringUtils.equals(userName, user.getUsername()))
        {
            return loginUserToUserOnline(user);
        }
        return null;
    }

    /**
     * 通过登录地址/用户名称查询信息
     * 
     * @param ipaddr 登录地址
     * @param userName 用户名称
     * @param user 用户信息
     * @return 在线用户信息
     */
    @Override
    public SysMemberOnline selectOnlineByInfo(String ipaddr, String userName, AppLoginUser user)
    {
        if (StringUtils.equals(ipaddr, user.getIpaddr()) && StringUtils.equals(userName, user.getUsername()))
        {
            return loginUserToUserOnline(user);
        }
        return null;
    }

    /**
     * 设置在线用户信息
     * 
     * @param user 用户信息
     * @return 在线用户
     */
    @Override
    public SysMemberOnline loginUserToUserOnline(AppLoginUser user)
    {
        if (StringUtils.isNull(user) || StringUtils.isNull(user.getUser()))
        {
            return null;
        }
        SysMemberOnline sysMemberOnline = new SysMemberOnline();
        sysMemberOnline.setTokenId(user.getToken());
        sysMemberOnline.setUserName(user.getUsername());
        sysMemberOnline.setIpaddr(user.getIpaddr());
        sysMemberOnline.setLoginLocation(user.getLoginLocation());
        sysMemberOnline.setBrowser(user.getBrowser());
        sysMemberOnline.setOs(user.getOs());
        sysMemberOnline.setLoginTime(user.getLoginTime());
        sysMemberOnline.setNickName(user.getUser().getAccount());
        return sysMemberOnline;
    }
}
