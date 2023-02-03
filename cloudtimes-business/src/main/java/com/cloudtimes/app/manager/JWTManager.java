package com.cloudtimes.app.manager;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cloudtimes.app.config.JWTConfig;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Calendar;

@Component
public class JWTManager {

    @Autowired
    private JWTConfig jwtConfig;

    public static final String AUTH_USER = "AuthUser";

    private static JWTManager instance;

    public static JWTManager getInstance() {
        return instance;
    }

    @PostConstruct
    public void init() {
        instance = this;
    }

    /**
     * 生成token
     */
    public String createToken(AuthUser authUser) {
        Calendar instance = Calendar.getInstance();
        //默认7天过期
        instance.add(Calendar.MINUTE, jwtConfig.getExpireTime());
        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        builder.withClaim(AUTH_USER, JSONObject.toJSONString(authUser));
        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(jwtConfig.getSecret()));
        return token;
    }

    /**
     * 获取认证对象
     *
     * @param token
     * @return
     */
    public AuthUser getAuthUser(String token) {
        String authUserString = this.verify(token).getPayload();
        return (AuthUser) JSONUtils.parse(authUserString);
    }

    /**
     * 验证token合法性
     */
    public DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(jwtConfig.getSecret())).build().verify(token);
    }

}
