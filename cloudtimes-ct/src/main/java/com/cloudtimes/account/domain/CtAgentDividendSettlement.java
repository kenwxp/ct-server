package com.cloudtimes.account.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;

/**
 * 分润结算审核对象 ct_agent_dividend_settlement
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
public class CtAgentDividendSettlement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private String id;

    /** 代理会员编号 */
    @Excel(name = "代理会员编号")
    private String userId;

    /** 门店编号 */
    @Excel(name = "门店编号")
    private String storeId;

    /** 分润年份 */
    @Excel(name = "分润年份")
    private Long dividendYear;

    /** 分润月份 */
    @Excel(name = "分润月份")
    private Long dividendMonth;

    /** 结算金额 */
    @Excel(name = "结算金额")
    private BigDecimal settleAmount;

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

    /** 平台审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "平台审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date platformApprovedTime;

    /** 代理审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "代理审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date agentApprovedTime;

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
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setStoreId(String storeId) 
    {
        this.storeId = storeId;
    }

    public String getStoreId() 
    {
        return storeId;
    }
    public void setDividendYear(Long dividendYear) 
    {
        this.dividendYear = dividendYear;
    }

    public Long getDividendYear() 
    {
        return dividendYear;
    }
    public void setDividendMonth(Long dividendMonth) 
    {
        this.dividendMonth = dividendMonth;
    }

    public Long getDividendMonth() 
    {
        return dividendMonth;
    }
    public void setSettleAmount(BigDecimal settleAmount) 
    {
        this.settleAmount = settleAmount;
    }

    public BigDecimal getSettleAmount() 
    {
        return settleAmount;
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
            .append("userId", getUserId())
            .append("storeId", getStoreId())
            .append("dividendYear", getDividendYear())
            .append("dividendMonth", getDividendMonth())
            .append("settleAmount", getSettleAmount())
            .append("isAgentOk", getIsAgentOk())
            .append("isPlatformOk", getIsPlatformOk())
            .append("state", getState())
            .append("delFlag", getDelFlag())
            .append("platformApprovedTime", getPlatformApprovedTime())
            .append("agentApprovedTime", getAgentApprovedTime())
            .append("createDate", getCreateDate())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
