package com.cloudtimes.partner.weixin.cache;

import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.cache.AuthCacheConfig;
import me.zhyd.oauth.cache.AuthStateCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * 自定义AuthState Rds 缓存
 *
 * @author zak
 **/
@Slf4j
@Component
public class AuthStateRedisCache implements AuthStateCache {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 存入缓存，默认3分钟
     *
     * @param key   缓存key
     * @param value 缓存内容
     */
    @Override
    public void cache(String key, String value) {
        log.info("RDS, 存入缓存，默认3分钟, key: {}, value: {}", key, value);
        stringRedisTemplate.opsForValue().set(key, value, AuthCacheConfig.timeout, TimeUnit.MILLISECONDS);
    }

    /**
     * 存入缓存
     *
     * @param key     缓存key
     * @param value   缓存内容
     * @param timeout 指定缓存过期时间（毫秒）
     */
    @Override
    public void cache(String key, String value, long timeout) {
        log.info("RDS, 存入缓存, key: {}, value: {}, timeout: {}", key, value, timeout);
        stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.MILLISECONDS);
    }

    /**
     * 获取缓存内容
     *
     * @param key 缓存key
     * @return 缓存内容
     */
    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 是否存在key，如果对应key的value值已过期，也返回false
     *
     * @param key 缓存key
     * @return true：存在key，并且value没过期；false：key不存在或者已过期
     */
    @Override
    public boolean containsKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }
}

