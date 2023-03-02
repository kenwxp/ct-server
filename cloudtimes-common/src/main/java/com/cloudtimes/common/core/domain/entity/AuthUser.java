package com.cloudtimes.common.core.domain.entity;

import com.cloudtimes.common.enums.ChannelType;
import lombok.Data;

import java.util.Date;

@Data
public class AuthUser {

    /**
     * 序列号
     */
    private String id;

    /**
     * 渠道
     */
    private ChannelType channelType;

    /**
     * 登录账号
     */
    private String account;


    /**
     * 微信openId
     */
    private String wxOpenId;

    /**
     * 微信unionId
     */
    private String wxUnionId;

    /**
     * 电话号码
     */
    private String mobile;

    /**
     * 是否代理
     */
    private String isAgent;

    /**
     * 是否店老板
     */
    private String isShopBoss;

    /**
     * 最近登录IP
     */
    private String lastLoginIp;

    /**
     * 最近登录时间
     */
    private Date lastLoginTime;

    /**
     * 代理状态
     */
    private String agentState;

    /**
     * 代理类型
     */
    private String agentType;

    /**
     * 店主状态
     */
    private String bossState;

    /**
     * 顾客状态
     */
    private String customerState;

    public AuthUser() {
    }

    public AuthUser(String id, ChannelType channelType) {
        this.id = id;
        this.channelType = channelType;
    }
}
