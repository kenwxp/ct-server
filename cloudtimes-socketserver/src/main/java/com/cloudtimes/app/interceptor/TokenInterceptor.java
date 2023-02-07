package com.cloudtimes.app.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.cloudtimes.app.manager.JWTManager;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.utils.AuthUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class TokenInterceptor implements HandshakeInterceptor {

    private static final String HEADER = "Authorization";

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        final String queryString = request.getURI().getQuery();
        final Map<String, String> queryMap = mapQueryString(queryString);
        if (queryMap.containsKey(HEADER)) {
            try {
                //验证令牌
                String token = queryMap.get(HEADER);
                AuthUser user = JWTManager.getInstance().getAuthUser(token);
                AuthUtils.setObject(user);
                // attributes是可以用来绑定一些自定义的数据到当前session上，在session的整个生命周期内都可以获取到
                attributes.put(JWTManager.AUTH_USER, user);
                attributes.put(HEADER, token);
                // 校验成功返回true，失败返回false，拒绝连接
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
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }

    private Map<String, String> mapQueryString(String queryString) {
        Map<String, String> paramMap = new HashMap<>();
        if (StringUtils.isEmpty(queryString)) {
            return paramMap;
        }
        final String[] kvArray = queryString.split("&");
        for (String kv : kvArray) {
            final String[] kvPair = kv.split("=");
            if (kvPair.length != 2) {
                continue;
            }
            String key = kvPair[0];
            String value = kvPair[1];
            if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
                paramMap.put(key, value);
            }
        }
        return paramMap;
    }
}
