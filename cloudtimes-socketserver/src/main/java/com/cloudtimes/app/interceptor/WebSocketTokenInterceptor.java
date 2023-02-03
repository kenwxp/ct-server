package com.cloudtimes.app.interceptor;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.cloudtimes.app.manager.JWTManager;
import com.cloudtimes.common.constant.UserConstants;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.utils.AuthUtils;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class WebSocketTokenInterceptor implements HandshakeInterceptor {

    private static final String HEADER = "Authorization";


    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> attributes) throws Exception {
        // 获得请求参数
        Map<String, String> paramMap = HttpUtil.decodeParamMap(serverHttpRequest.getURI().getQuery(), Charset.forName("utf-8"));
        String token = paramMap.get(HEADER);
        if (StrUtil.isNotBlank(token)) {
            try {
                //验证令牌
                AuthUser user = JWTManager.getInstance().getAuthUser(token);
                attributes.put(UserConstants.AUTH_USER, user);
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
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        log.info("握手成功...");
        try {
            serverHttpResponse.getBody().write(JSONObject.toJSONBytes(AjaxResult.success("WebSocket连接成功!")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
