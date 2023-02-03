package com.cloudtimes.account.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;

/**
 * 用户对象 ct_user
 *
 * @author 沈兵
 * @date 2023-01-17
 */
public class CtUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 序列号
     */
    private String id;

    /**
     * 登录账号
     */
    @Excel(name = "登录账号")
    private String account;
    /**
     * 微信openId
     */
    @Excel(name = "微信openId")
    private String wxOpenId;
    /**
     * 微信unionId
     */
    @Excel(name = "微信unionId")
    private String wxUnionId;
    /**
     * 登录密码
     */
    @Excel(name = "登录密码")
    private String password;

    /**
     * 电话号码
     */
    @Excel(name = "电话号码")
    private String mobile;

    /**
     * 现金余额
     */
    @Excel(name = "现金余额")
    private BigDecimal moneyAmount;

    /**
     * 积分余额
     */
    @Excel(name = "积分余额")
    private BigDecimal scoreAmount;

    /**
     * 累计已提现
     */
    @Excel(name = "累计已提现")
    private BigDecimal totalWithdrawal;

    /**
     * 信用分
     */
    @Excel(name = "信用分")
    private Long creditScore;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 真实姓名
     */
    @Excel(name = "真实姓名")
    private String realName;

    /**
     * 性别
     */
    @Excel(name = "性别")
    private String sex;

    /**
     * 生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 是否实名
     */
    @Excel(name = "是否实名")
    private String isReal;

    /**
     * 违规次数
     */
    @Excel(name = "违规次数")
    private Long violationCount;

    /**
     * 身份证号
     */
    @Excel(name = "身份证号")
    private String idNo;

    /**
     * 是否代理
     */
    @Excel(name = "是否代理")
    private String isAgent;

    /**
     * 注册门店编号
     */
    @Excel(name = "注册门店编号")
    private String registerStoreId;

    /**
     * 是否店老板
     */
    @Excel(name = "是否店老板")
    private String isShopBoss;

    /**
     * 最近登录IP
     */
    @Excel(name = "最近登录IP")
    private String lastLoginIp;

    /**
     * 最近登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最近登录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastLoginTime;

    /**
     * 成为店主时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "成为店主时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createBossTime;

    /**
     * 成为代理时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "成为代理时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createAgentTime;

    /**
     * 代理状态
     */
    @Excel(name = "代理状态")
    private String agentState;

    /**
     * 代理类型
     */
    @Excel(name = "代理类型")
    private String agentType;

    /**
     * 店主状态
     */
    @Excel(name = "店主状态")
    private String bossState;

    /**
     * 顾客状态
     */
    @Excel(name = "顾客状态")
    private String customerState;

    /**
     * 操作管理员
     */
    @Excel(name = "操作管理员")
    private String operator;

    /**
     * 注册日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "注册日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    /**
     * 是否删除
     */
    private String delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public BigDecimal getScoreAmount() {
        return scoreAmount;
    }

    public void setScoreAmount(BigDecimal scoreAmount) {
        this.scoreAmount = scoreAmount;
    }

    public BigDecimal getTotalWithdrawal() {
        return totalWithdrawal;
    }

    public void setTotalWithdrawal(BigDecimal totalWithdrawal) {
        this.totalWithdrawal = totalWithdrawal;
    }

    public Long getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Long creditScore) {
        this.creditScore = creditScore;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIsReal() {
        return isReal;
    }

    public void setIsReal(String isReal) {
        this.isReal = isReal;
    }

    public Long getViolationCount() {
        return violationCount;
    }

    public void setViolationCount(Long violationCount) {
        this.violationCount = violationCount;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIsAgent() {
        return isAgent;
    }

    public void setIsAgent(String isAgent) {
        this.isAgent = isAgent;
    }

    public String getRegisterStoreId() {
        return registerStoreId;
    }

    public void setRegisterStoreId(String registerStoreId) {
        this.registerStoreId = registerStoreId;
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

    public Date getCreateBossTime() {
        return createBossTime;
    }

    public void setCreateBossTime(Date createBossTime) {
        this.createBossTime = createBossTime;
    }

    public Date getCreateAgentTime() {
        return createAgentTime;
    }

    public void setCreateAgentTime(Date createAgentTime) {
        this.createAgentTime = createAgentTime;
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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "CtUser{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", wxOpenId='" + wxOpenId + '\'' +
                ", wxUnionId='" + wxUnionId + '\'' +
                ", mobile='" + mobile + '\'' +
                ", moneyAmount=" + moneyAmount +
                ", scoreAmount=" + scoreAmount +
                ", totalWithdrawal=" + totalWithdrawal +
                ", creditScore=" + creditScore +
                ", nickName='" + nickName + '\'' +
                ", realName='" + realName + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", isReal='" + isReal + '\'' +
                ", violationCount=" + violationCount +
                ", idNo='" + idNo + '\'' +
                ", isAgent='" + isAgent + '\'' +
                ", registerStoreId='" + registerStoreId + '\'' +
                ", isShopBoss='" + isShopBoss + '\'' +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", createBossTime=" + createBossTime +
                ", createAgentTime=" + createAgentTime +
                ", agentState='" + agentState + '\'' +
                ", agentType='" + agentType + '\'' +
                ", bossState='" + bossState + '\'' +
                ", customerState='" + customerState + '\'' +
                ", operator='" + operator + '\'' +
                ", createDate=" + createDate +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}
