package com.cloudtimes.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cloudtimes.common.config.JWTConfig;
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
     * 生产jwt token
     *
     * @param authUser
     * @param expire   失效时间 单位分钟，设置为0时为永久
     * @return
     */
    public String createToken(AuthUser authUser, int expire) {
        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        builder.withClaim(AUTH_USER, JSONObject.toJSONString(authUser));
        if (expire != 0) {
            Calendar instance = Calendar.getInstance();
            instance.add(Calendar.MINUTE, expire);
            return builder.withExpiresAt(instance.getTime())
                    .sign(Algorithm.HMAC256(jwtConfig.getSecret()));
        } else {
            return builder.sign(Algorithm.HMAC256(jwtConfig.getSecret()));
        }
    }

    /**
     * 生成token
     */
    public String createToken(AuthUser authUser) {
        // 默认有效时间，从配置中读取
        return createToken(authUser, jwtConfig.getExpireTime());
    }

    /**
     * 获取认证对象
     *
     * @param token
     * @return
     */
    public AuthUser getAuthUser(String token) {
        String authUserString = this.verify(token).getClaim(AUTH_USER).asString();
        return JSONObject.parseObject(authUserString, AuthUser.class);
    }

    /**
     * 验证token合法性
     */
    public DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(jwtConfig.getSecret())).build().verify(token);
    }

}
