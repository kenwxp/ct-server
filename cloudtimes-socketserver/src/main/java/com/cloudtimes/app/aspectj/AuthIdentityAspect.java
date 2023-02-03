package com.cloudtimes.app.aspectj;

import com.cloudtimes.common.annotation.AuthIdentity;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.utils.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 身份验证标识处理
 *
 * @author tank
 */
@Aspect
@Component
public class AuthIdentityAspect {

    private static final Logger log = LoggerFactory.getLogger(AuthIdentityAspect.class);

    @Before("@annotation(authIdentity)")
    public void doBefore(JoinPoint point, AuthIdentity authIdentity) throws Throwable {
        String identity = authIdentity.identity();
        if (!SecurityUtils.getAppLoginUser().isAuthIdentitySame(identity)) {
            throw new ServiceException("该身份认证无权访问!");
        }
    }
}
