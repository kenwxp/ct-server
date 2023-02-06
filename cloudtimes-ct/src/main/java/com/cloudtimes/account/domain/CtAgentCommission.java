package com.cloudtimes.account.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;

/**
 * 代理销售佣金设置对象 ct_agent_commission
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
public class CtAgentCommission extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 代理编号 */
    private String id;

    /** 上级代理编号 */
    @Excel(name = "上级代理编号")
    private String parentUserId;

    /** 系统价格 */
    @Excel(name = "系统价格")
    private BigDecimal systemPrice;

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
    public void setParentUserId(String parentUserId) 
    {
        this.parentUserId = parentUserId;
    }

    public String getParentUserId() 
    {
        return parentUserId;
    }
    public void setSystemPrice(BigDecimal systemPrice) 
    {
        this.systemPrice = systemPrice;
    }

    public BigDecimal getSystemPrice() 
    {
        return systemPrice;
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
            .append("parentUserId", getParentUserId())
            .append("systemPrice", getSystemPrice())
            .append("operator", getOperator())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("createDate", getCreateDate())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
