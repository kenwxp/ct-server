package com.cloudtimes.common.annotation;

import com.cloudtimes.common.constant.UserConstants;

import java.lang.annotation.*;

/**
 * 认证标识注解
 *
 * @author tank
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthIdentity {

    /**
     * 认证标识
     */
    String identity() default UserConstants.CUSTOMER_USER;
}
