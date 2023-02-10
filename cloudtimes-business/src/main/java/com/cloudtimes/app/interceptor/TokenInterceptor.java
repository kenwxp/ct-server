package com.cloudtimes.app.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.cloudtimes.common.utils.JWTManager;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.utils.AuthUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {

    private static final String HEADER = "Authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头中的令牌
        String token = request.getHeader(HEADER);
        //验证令牌
        try {
            //验证令牌
            AuthUser user = JWTManager.getInstance().getAuthUser(token);
            AuthUtils.setObject(user);
            return true;
        } catch (SignatureVerificationException e) {
            throw new ServiceException("无效签名");
        } catch (TokenExpiredException e) {
            throw new ServiceException("token过期");
        } catch (AlgorithmMismatchException e) {
            throw new ServiceException("token无效");
        } catch (Exception e) {
            throw new ServiceException("token无效");
        }
    }
}
