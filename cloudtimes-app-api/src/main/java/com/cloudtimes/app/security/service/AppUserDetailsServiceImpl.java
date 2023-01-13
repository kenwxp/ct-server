package com.cloudtimes.app.security.service;

import com.cloudtimes.common.core.domain.entity.YbfMember;
import com.cloudtimes.common.core.domain.model.AppLoginUser;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户验证处理
 *
 * @author tank
 */
@Service
public class AppUserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(AppUserDetailsServiceImpl.class);

    @Autowired
    private AppPasswordService passwordService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        YbfMember user = null;//iYbfMemberService.selectYbfMemberByUsername(username);
        if (StringUtils.isNull(user)) {
            log.info("登录用户：{} 不存在.", username);
            throw new ServiceException("登录用户：" + username + " 不存在");
        } else if (user.getEnabled().equals("1")) {
            log.info("登录用户：{} 已被管理员停用.", username);
            throw new ServiceException("对不起，您的账号：" + username + " 已被管理员停用");
        } else if (user.getEnabled().equals("2")) {
            log.info("登录用户：{} 已被平台锁定", username);
            throw new ServiceException("对不起，您的账号：" + username + " 已被平台锁定");
        } else if (user.getEnabled().equals("3")) {
            log.info("登录用户：{} 已被平台注销", username);
            throw new ServiceException("对不起，您的账号：" + username + " 已被平台注销");
        }
        passwordService.validate(user);
        return createLoginUser(user);
    }

    public UserDetails createLoginUser(YbfMember user) {
        return new AppLoginUser(user.getId(), user);
    }
}
