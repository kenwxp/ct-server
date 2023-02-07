package com.cloudtimes.account.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;

/**
 * 代理对象 ct_user_agent
 * 
 * @author 沈兵
 * @date 2023-02-07
 */
public class CtUserAgent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户编号 */
    private String userId;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    private String nickName;

    /** 代理类型 */
    @Excel(name = "代理类型")
    private String agentType;

    /** 上级代理编号 */
    @Excel(name = "上级代理编号")
    private String parentUserId;

    /** 代理区域 */
    @Excel(name = "代理区域")
    private String agentArea;

    /** 代理现金余额 */
    @Excel(name = "代理现金余额")
    private BigDecimal cashAmount;

    /** 代理累计已提现 */
    @Excel(name = "代理累计已提现")
    private BigDecimal totalWithdrawal;

    /** 累计销售提成(累计产品销售佣金) */
    @Excel(name = "累计销售提成(累计产品销售佣金)")
    private BigDecimal totalSalesReward;

    /** 累计分润提成(应收分成) */
    @Excel(name = "累计分润提成(应收分成)")
    private BigDecimal totalDividend;

    /** 累计活动提成(平台活动奖励) */
    @Excel(name = "累计活动提成(平台活动奖励)")
    private BigDecimal totalActivityReward;

    /** 累计下级贡献提成 */
    @Excel(name = "累计下级贡献提成")
    private BigDecimal totalTributes;

    /** 创建日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    /** 是否删除 */
    private String delFlag;

    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getNickName() 
    {
        return nickName;
    }
    public void setAgentType(String agentType) 
    {
        this.agentType = agentType;
    }

    public String getAgentType() 
    {
        return agentType;
    }
    public void setParentUserId(String parentUserId) 
    {
        this.parentUserId = parentUserId;
    }

    public String getParentUserId() 
    {
        return parentUserId;
    }
    public void setAgentArea(String agentArea) 
    {
        this.agentArea = agentArea;
    }

    public String getAgentArea() 
    {
        return agentArea;
    }
    public void setCashAmount(BigDecimal cashAmount) 
    {
        this.cashAmount = cashAmount;
    }

    public BigDecimal getCashAmount() 
    {
        return cashAmount;
    }
    public void setTotalWithdrawal(BigDecimal totalWithdrawal) 
    {
        this.totalWithdrawal = totalWithdrawal;
    }

    public BigDecimal getTotalWithdrawal() 
    {
        return totalWithdrawal;
    }
    public void setTotalSalesReward(BigDecimal totalSalesReward) 
    {
        this.totalSalesReward = totalSalesReward;
    }

    public BigDecimal getTotalSalesReward() 
    {
        return totalSalesReward;
    }
    public void setTotalDividend(BigDecimal totalDividend) 
    {
        this.totalDividend = totalDividend;
    }

    public BigDecimal getTotalDividend() 
    {
        return totalDividend;
    }
    public void setTotalActivityReward(BigDecimal totalActivityReward) 
    {
        this.totalActivityReward = totalActivityReward;
    }

    public BigDecimal getTotalActivityReward() 
    {
        return totalActivityReward;
    }
    public void setTotalTributes(BigDecimal totalTributes) 
    {
        this.totalTributes = totalTributes;
    }

    public BigDecimal getTotalTributes() 
    {
        return totalTributes;
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
            .append("userId", getUserId())
            .append("nickName", getNickName())
            .append("agentType", getAgentType())
            .append("parentUserId", getParentUserId())
            .append("agentArea", getAgentArea())
            .append("cashAmount", getCashAmount())
            .append("totalWithdrawal", getTotalWithdrawal())
            .append("totalSalesReward", getTotalSalesReward())
            .append("totalDividend", getTotalDividend())
            .append("totalActivityReward", getTotalActivityReward())
            .append("totalTributes", getTotalTributes())
            .append("remark", getRemark())
            .append("createDate", getCreateDate())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
