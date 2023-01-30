package com.cloudtimes.account.domain;

import java.math.BigDecimal;
import java.util.Date;
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
public class CtUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序列号 */
    private Long id;

    /** 用户编码 */
    @Excel(name = "用户编码")
    private String userCode;

    /** 登录账号 */
    @Excel(name = "登录账号")
    private String account;

    /** 登录密码 */
    @Excel(name = "登录密码")
    private String password;

    /** 微信openId */
    @Excel(name = "微信openId")
    private String wxOpenId;

    /** 电话号码 */
    @Excel(name = "电话号码")
    private String mobile;

    /** 现金余额 */
    @Excel(name = "现金余额")
    private BigDecimal moneyAmount;

    /** 积分余额 */
    @Excel(name = "积分余额")
    private BigDecimal scoreAmount;

    /** 信用分 */
    @Excel(name = "信用分")
    private Long creditScore;

    /** 昵称 */
    private String nickName;

    /** 真实姓名 */
    @Excel(name = "真实姓名")
    private String realName;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 是否实名 */
    @Excel(name = "是否实名")
    private String isReal;

    /** 违规次数 */
    @Excel(name = "违规次数")
    private Long violationCount;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idNo;

    /** 是否代理 */
    @Excel(name = "是否代理")
    private String isAgent;

    /** 是否店老板 */
    @Excel(name = "是否店老板")
    private String isShopBoss;

    /** 最近登录IP */
    @Excel(name = "最近登录IP")
    private String lastLoginIp;

    /** 最近登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最近登录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastLoginTime;

    /** 成为店主时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "成为店主时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createBossTime;

    /** 成为代理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "成为代理时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createAgentTime;

    /** 代理状态 */
    @Excel(name = "代理状态")
    private String agentState;

    /** 代理类型 */
    @Excel(name = "代理类型")
    private String agentType;

    /** 店主状态 */
    @Excel(name = "店主状态")
    private String bossState;

    /** 顾客状态 */
    @Excel(name = "顾客状态")
    private String customerState;

    /** 操作管理员 */
    @Excel(name = "操作管理员")
    private String operator;

    /** 注册日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "注册日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    /** 是否删除 */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserCode(String userCode) 
    {
        this.userCode = userCode;
    }

    public String getUserCode() 
    {
        return userCode;
    }
    public void setAccount(String account) 
    {
        this.account = account;
    }

    public String getAccount() 
    {
        return account;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setWxOpenId(String wxOpenId) 
    {
        this.wxOpenId = wxOpenId;
    }

    public String getWxOpenId() 
    {
        return wxOpenId;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setMoneyAmount(BigDecimal moneyAmount) 
    {
        this.moneyAmount = moneyAmount;
    }

    public BigDecimal getMoneyAmount() 
    {
        return moneyAmount;
    }
    public void setScoreAmount(BigDecimal scoreAmount) 
    {
        this.scoreAmount = scoreAmount;
    }

    public BigDecimal getScoreAmount() 
    {
        return scoreAmount;
    }
    public void setCreditScore(Long creditScore) 
    {
        this.creditScore = creditScore;
    }

    public Long getCreditScore() 
    {
        return creditScore;
    }
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getNickName() 
    {
        return nickName;
    }
    public void setRealName(String realName) 
    {
        this.realName = realName;
    }

    public String getRealName() 
    {
        return realName;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setBirthday(Date birthday) 
    {
        this.birthday = birthday;
    }

    public Date getBirthday() 
    {
        return birthday;
    }
    public void setIsReal(String isReal) 
    {
        this.isReal = isReal;
    }

    public String getIsReal() 
    {
        return isReal;
    }
    public void setViolationCount(Long violationCount) 
    {
        this.violationCount = violationCount;
    }

    public Long getViolationCount() 
    {
        return violationCount;
    }
    public void setIdNo(String idNo) 
    {
        this.idNo = idNo;
    }

    public String getIdNo() 
    {
        return idNo;
    }
    public void setIsAgent(String isAgent) 
    {
        this.isAgent = isAgent;
    }

    public String getIsAgent() 
    {
        return isAgent;
    }
    public void setIsShopBoss(String isShopBoss) 
    {
        this.isShopBoss = isShopBoss;
    }

    public String getIsShopBoss() 
    {
        return isShopBoss;
    }
    public void setLastLoginIp(String lastLoginIp) 
    {
        this.lastLoginIp = lastLoginIp;
    }

    public String getLastLoginIp() 
    {
        return lastLoginIp;
    }
    public void setLastLoginTime(Date lastLoginTime) 
    {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getLastLoginTime() 
    {
        return lastLoginTime;
    }
    public void setCreateBossTime(Date createBossTime) 
    {
        this.createBossTime = createBossTime;
    }

    public Date getCreateBossTime() 
    {
        return createBossTime;
    }
    public void setCreateAgentTime(Date createAgentTime) 
    {
        this.createAgentTime = createAgentTime;
    }

    public Date getCreateAgentTime() 
    {
        return createAgentTime;
    }
    public void setAgentState(String agentState) 
    {
        this.agentState = agentState;
    }

    public String getAgentState() 
    {
        return agentState;
    }
    public void setAgentType(String agentType) 
    {
        this.agentType = agentType;
    }

    public String getAgentType() 
    {
        return agentType;
    }
    public void setBossState(String bossState) 
    {
        this.bossState = bossState;
    }

    public String getBossState() 
    {
        return bossState;
    }
    public void setCustomerState(String customerState) 
    {
        this.customerState = customerState;
    }

    public String getCustomerState() 
    {
        return customerState;
    }
    public void setOperator(String operator) 
    {
        this.operator = operator;
    }

    public String getOperator() 
    {
        return operator;
    }
    public void setCreateDate(Date createDate) 
    {
        this.createDate = createDate;
    }

    public Date getCreateDate() 
    {
        return createDate;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userCode", getUserCode())
            .append("account", getAccount())
            .append("password", getPassword())
            .append("wxOpenId", getWxOpenId())
            .append("mobile", getMobile())
            .append("moneyAmount", getMoneyAmount())
            .append("scoreAmount", getScoreAmount())
            .append("creditScore", getCreditScore())
            .append("nickName", getNickName())
            .append("realName", getRealName())
            .append("sex", getSex())
            .append("birthday", getBirthday())
            .append("isReal", getIsReal())
            .append("violationCount", getViolationCount())
            .append("idNo", getIdNo())
            .append("isAgent", getIsAgent())
            .append("isShopBoss", getIsShopBoss())
            .append("lastLoginIp", getLastLoginIp())
            .append("lastLoginTime", getLastLoginTime())
            .append("createBossTime", getCreateBossTime())
            .append("createAgentTime", getCreateAgentTime())
            .append("agentState", getAgentState())
            .append("agentType", getAgentType())
            .append("bossState", getBossState())
            .append("customerState", getCustomerState())
            .append("operator", getOperator())
            .append("createDate", getCreateDate())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
