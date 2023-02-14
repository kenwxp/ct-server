package com.cloudtimes.serving.mobile.service.impl;

import com.cloudtimes.account.domain.CtUser;
import com.cloudtimes.account.mapper.CtUserMapper;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.utils.SecurityUtils;
import com.cloudtimes.common.utils.StringUtils;
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
    public CtUser shopBossRegister(String phone, String password, String account, String nickName) {
        CtUser dbUser = userMapper.selectCtUserByMobile(phone);
        if (dbUser != null) {
            throw new ServiceException("用户已存在");
        }
        //  加密密码
        String encryptPassword = SecurityUtils.encryptPassword(password);
        CtUser newUser = new CtUser();
        newUser.setAccount(account);
        newUser.setPassword(encryptPassword);
        newUser.setMobile(phone);
        newUser.setNickName(nickName);
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
    public CtUser shopBossLogin(String phone, String password, String loginIp) {
        CtUser dbUser = userMapper.selectCtUserByMobile(phone);
        if (dbUser == null) {
            throw new ServiceException("用户名或密码不正确");
        }
        if (!SecurityUtils.matchesPassword(password, dbUser.getPassword())) {
            throw new ServiceException("用户名或密码不正确");
        }
        if (!"1".equals(dbUser.isShopBoss())) {
            throw new ServiceException("用户非店老板");
        }
        dbUser.setLastLoginTime(new Date());
        dbUser.setLastLoginIp(loginIp);
        if (userMapper.updateCtUser(dbUser) < 1) {
            throw new ServiceException("更新用户失败");
        }
        return dbUser;
    }

    @Override
    public boolean changePassword(String userId, String newPassword, String oldPassword) {
        CtUser dbUser = userMapper.selectCtUserById(userId);
        if (dbUser == null) {
            throw new ServiceException("无法获取用户信息");
        }
        String oldEncrypt = SecurityUtils.encryptPassword(oldPassword);
        String newEncrypt = SecurityUtils.encryptPassword(newPassword);
        if (!StringUtils.equals(oldEncrypt, dbUser.getPassword())) {
            throw new ServiceException("旧密码错误，请确认");
        }
        dbUser.setPassword(newEncrypt);
        if (userMapper.updateCtUser(dbUser) < 1) {
            return false;
        }
        return true;
    }
}

