package com.cloudtimes.account.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;

/**
 * 分润配置对象 ct_agent_dividend
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
public class CtAgentDividend extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private String id;

    /** 代理用户编号 */
    @Excel(name = "代理用户编号")
    private String userId;

    /** 营收金额 */
    @Excel(name = "营收金额")
    private BigDecimal billAmount;

    /** 提成比例 */
    @Excel(name = "提成比例")
    private BigDecimal dividendRatio;

    /** 操作管理员 */
    @Excel(name = "操作管理员")
    private String operator;

    /** 是否删除 */
    private String delFlag;

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
    public void setBillAmount(BigDecimal billAmount) 
    {
        this.billAmount = billAmount;
    }

    public BigDecimal getBillAmount() 
    {
        return billAmount;
    }
    public void setDividendRatio(BigDecimal dividendRatio) 
    {
        this.dividendRatio = dividendRatio;
    }

    public BigDecimal getDividendRatio() 
    {
        return dividendRatio;
    }
    public void setOperator(String operator) 
    {
        this.operator = operator;
    }

    public String getOperator() 
    {
        return operator;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
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
            .append("billAmount", getBillAmount())
            .append("dividendRatio", getDividendRatio())
            .append("operator", getOperator())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("createDate", getCreateDate())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
