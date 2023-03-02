package com.cloudtimes.app.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.cloudtimes.app.constant.PrefixPathConstants;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.utils.AuthUtils;
import com.cloudtimes.common.utils.JWTManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
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
            String contextPath = request.getContextPath();
            String requestURI = request.getRequestURI();
            if ((StringUtils.startsWith(requestURI, contextPath + PrefixPathConstants.MOBILE_PATH_PREFIX)
                    && user.getChannelType() != ChannelType.MOBILE) &&
                    (StringUtils.startsWith(requestURI, contextPath + PrefixPathConstants.WX_MP_PATH_PREFIX)
                            && user.getChannelType() != ChannelType.WX_MP) &&
                    (StringUtils.startsWith(requestURI, contextPath + PrefixPathConstants.WX_OFFICIAL_PATH_PREFIX)
                            && user.getChannelType() != ChannelType.WX_OFFICIAL) &&
                    (StringUtils.startsWith(requestURI, contextPath + PrefixPathConstants.CASH_PATH_PREFIX)
                            && user.getChannelType() != ChannelType.CASH) &&
                    (StringUtils.startsWith(requestURI, contextPath + PrefixPathConstants.DOOR_FACE_PATH_PREFIX)
                            && user.getChannelType() != ChannelType.DOOR_FACE)) {
                throw new ServiceException("渠道类型错误");
            }
            return true;
        } catch (SignatureVerificationException e) {
            throw new ServiceException("无效签名");
        } catch (TokenExpiredException e) {
            throw new ServiceException("token过期");
        } catch (AlgorithmMismatchException e) {
            throw new ServiceException("token无效");
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("token无效");
        }
    }
}
