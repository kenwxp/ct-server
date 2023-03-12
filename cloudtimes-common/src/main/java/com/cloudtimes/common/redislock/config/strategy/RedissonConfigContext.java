package com.cloudtimes.common.redislock.config.strategy;

import com.cloudtimes.common.redislock.config.RedissonProperties;
import org.redisson.config.Config;

/**
 * @desc Redisson配置上下文，产出真正的Redisson的Config
 */
public class RedissonConfigContext {

    private RedissonConfigStrategy redissonConfigStrategy = null;

    public RedissonConfigContext(RedissonConfigStrategy _redissonConfigStrategy) {
        this.redissonConfigStrategy = _redissonConfigStrategy;
    }

    /**
     * 上下文根据构造中传入的具体策略产出真实的Redisson的Config
     *
     * @param redissonProperties
     * @return
     */
    public Config createRedissonConfig(RedissonProperties redissonProperties) {
        return this.redissonConfigStrategy.createRedissonConfig(redissonProperties);
    }
}
