package com.cloudtimes.account.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;

/**
 * 代理活动奖励维护对象 ct_agent_activity_reward
 * 
 * @author 沈兵
 * @date 2023-02-02
 */
public class CtAgentActivityReward extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private String id;

    /** 活动编号 */
    @Excel(name = "活动编号")
    private String activityId;

    /** 代理用户编号 */
    @Excel(name = "代理用户编号")
    private String userId;

    /** 新开店数量 */
    @Excel(name = "新开店数量")
    private Long storeCount;

    /** 奖励金额 */
    @Excel(name = "奖励金额")
    private BigDecimal rewardMoney;

    /** 结算时长条件 */
    @Excel(name = "结算时长条件")
    private Long settleTimes;

    /** 平台审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "平台审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date platformApprovedTime;

    /** 代理审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "代理审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date agentApprovedTime;

    /** 是否已达成 */
    @Excel(name = "是否已达成")
    private String isFulfilled;

    /** 代理是否审核 */
    @Excel(name = "代理是否审核")
    private String isAgentOk;

    /** 平台是否审核 */
    @Excel(name = "平台是否审核")
    private String isPlatformOk;

    /** 结算状态 */
    @Excel(name = "结算状态")
    private String state;

    /** 是否删除 */
    private String delFlag;

    /** 操作管理员 */
    @Excel(name = "操作管理员")
    private String operator;

    /** 创建日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setActivityId(String activityId) 
    {
        this.activityId = activityId;
    }

    public String getActivityId() 
    {
        return activityId;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setStoreCount(Long storeCount) 
    {
        this.storeCount = storeCount;
    }

    public Long getStoreCount() 
    {
        return storeCount;
    }
    public void setRewardMoney(BigDecimal rewardMoney) 
    {
        this.rewardMoney = rewardMoney;
    }

    public BigDecimal getRewardMoney() 
    {
        return rewardMoney;
    }
    public void setSettleTimes(Long settleTimes) 
    {
        this.settleTimes = settleTimes;
    }

    public Long getSettleTimes() 
    {
        return settleTimes;
    }
    public void setPlatformApprovedTime(Date platformApprovedTime) 
    {
        this.platformApprovedTime = platformApprovedTime;
    }

    public Date getPlatformApprovedTime() 
    {
        return platformApprovedTime;
    }
    public void setAgentApprovedTime(Date agentApprovedTime) 
    {
        this.agentApprovedTime = agentApprovedTime;
    }

    public Date getAgentApprovedTime() 
    {
        return agentApprovedTime;
    }
    public void setIsFulfilled(String isFulfilled) 
    {
        this.isFulfilled = isFulfilled;
    }

    public String getIsFulfilled() 
    {
        return isFulfilled;
    }
    public void setIsAgentOk(String isAgentOk) 
    {
        this.isAgentOk = isAgentOk;
    }

    public String getIsAgentOk() 
    {
        return isAgentOk;
    }
    public void setIsPlatformOk(String isPlatformOk) 
    {
        this.isPlatformOk = isPlatformOk;
    }

    public String getIsPlatformOk() 
    {
        return isPlatformOk;
    }
    public void setState(String state) 
    {
        this.state = state;
    }

    public String getState() 
    {
        return state;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("activityId", getActivityId())
            .append("userId", getUserId())
            .append("storeCount", getStoreCount())
            .append("rewardMoney", getRewardMoney())
            .append("settleTimes", getSettleTimes())
            .append("platformApprovedTime", getPlatformApprovedTime())
            .append("agentApprovedTime", getAgentApprovedTime())
            .append("isFulfilled", getIsFulfilled())
            .append("isAgentOk", getIsAgentOk())
            .append("isPlatformOk", getIsPlatformOk())
            .append("state", getState())
            .append("delFlag", getDelFlag())
            .append("operator", getOperator())
            .append("remark", getRemark())
            .append("createDate", getCreateDate())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
