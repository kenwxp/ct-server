package com.cloudtimes.common.core.domain.entity;

import java.util.Date;

public class AuthUser {

    /**
     * 序列号
     */
    private String id;

    /**
     * 渠道
     */
    private String channelType;

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

    public AuthUser(String id, String channelType) {
        this.id = id;
        this.channelType = channelType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public String getWxUnionId() {
        return wxUnionId;
    }

    public void setWxUnionId(String wxUnionId) {
        this.wxUnionId = wxUnionId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIsAgent() {
        return isAgent;
    }

    public void setIsAgent(String isAgent) {
        this.isAgent = isAgent;
    }

    public String getIsShopBoss() {
        return isShopBoss;
    }

    public void setIsShopBoss(String isShopBoss) {
        this.isShopBoss = isShopBoss;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getAgentState() {
        return agentState;
    }

    public void setAgentState(String agentState) {
        this.agentState = agentState;
    }

    public String getAgentType() {
        return agentType;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }

    public String getBossState() {
        return bossState;
    }

    public void setBossState(String bossState) {
        this.bossState = bossState;
    }

    public String getCustomerState() {
        return customerState;
    }

    public void setCustomerState(String customerState) {
        this.customerState = customerState;
    }
}
