package com.cloudtimes.serving.mobile.service.impl;

import com.cloudtimes.account.domain.CtUser;
import com.cloudtimes.account.mapper.CtUserMapper;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.utils.SecurityUtils;
import com.cloudtimes.serving.mobile.domain.LoginReq;
import com.cloudtimes.serving.mobile.domain.LoginResp;
import com.cloudtimes.serving.mobile.domain.RegisterReq;
import com.cloudtimes.serving.mobile.service.ICtShopBossLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 小程序用户登录Service业务层处理
 *
 * @author wangxp
 * @date 2023-02-02
 */
@DataSource(DataSourceType.CT)
@Service
public class CtShopBossLoginServiceImpl implements ICtShopBossLoginService {

    @Autowired
    private CtUserMapper userMapper;

    @Override
    public CtUser shopBossRegister(RegisterReq param) {
        CtUser dbUser = userMapper.selectCtUserByMobile(param.getPhone());
        if (dbUser != null) {
            throw new ServiceException("用户已存在");
        }
        //  加密密码
        String encryptPassword = SecurityUtils.encryptPassword(param.getPassword());
        CtUser newUser = new CtUser();
        newUser.setAccount(param.getAccount());
        newUser.setPassword(encryptPassword);
        newUser.setMobile(param.getPhone());
        newUser.setNickName(param.getNickName());
        newUser.setIsAgent("0");
        newUser.setIsShopBoss("1");
        newUser.setCreateBossTime(new Date());
        newUser.setBossState("0");
        newUser.setCreateDate(new Date());
        newUser.setDelFlag("0");
        if (userMapper.insertCtUser(newUser) < 1) {
            throw new ServiceException("新增用户失败");
        }
        return newUser;
    }

    @Override
    public LoginResp shopBossLogin(LoginReq param, String loginIp) {
        CtUser dbUser = userMapper.selectCtUserByMobile(param.getPhone());
        if (dbUser == null) {
            throw new ServiceException("用户名或密码不正确");
        }
        if (!SecurityUtils.matchesPassword(param.getPassword(), dbUser.getPassword())) {
            throw new ServiceException("用户名或密码不正确");
        }
        if (!"1".equals(dbUser.getIsShopBoss())) {
            throw new ServiceException("用户非店老板");
        }
        dbUser.setLastLoginTime(new Date());
        dbUser.setLastLoginIp(loginIp);
        if (userMapper.updateCtUser(dbUser) < 1) {
            throw new ServiceException("更新用户失败");
        }
        LoginResp loginResp = new LoginResp();
        loginResp.setId(dbUser.getId());
        loginResp.setName(dbUser.getNickName());
        loginResp.setPhone(dbUser.getMobile());
        return loginResp;
    }


}

