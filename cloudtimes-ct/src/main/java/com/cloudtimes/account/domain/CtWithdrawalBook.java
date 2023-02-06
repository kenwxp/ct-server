package com.cloudtimes.account.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;

/**
 * 提现登记薄对象 ct_withdrawal_book
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
public class CtWithdrawalBook extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private String id;

    /** 用户编号 */
    @Excel(name = "用户编号")
    private String userId;

    /** 用户类型 */
    @Excel(name = "用户类型")
    private String userType;

    /** 提现金额 */
    @Excel(name = "提现金额")
    private BigDecimal amount;

    /** 支付序列号 */
    @Excel(name = "支付序列号")
    private String paySerial;

    /** 第三方支付序列号 */
    @Excel(name = "第三方支付序列号")
    private String thirdSerial;

    /** 支付状态 */
    @Excel(name = "支付状态")
    private String payState;

    /** 申请日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date applyDate;

    /** 支付日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "支付日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payDate;

    /** 支付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "支付时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payTime;

    /** 操作管理员 */
    @Excel(name = "操作管理员")
    private String operator;

    /** 是否删除 */
    private String delFlag;

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
    public void setUserType(String userType) 
    {
        this.userType = userType;
    }

    public String getUserType() 
    {
        return userType;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }
    public void setPaySerial(String paySerial) 
    {
        this.paySerial = paySerial;
    }

    public String getPaySerial() 
    {
        return paySerial;
    }
    public void setThirdSerial(String thirdSerial) 
    {
        this.thirdSerial = thirdSerial;
    }

    public String getThirdSerial() 
    {
        return thirdSerial;
    }
    public void setPayState(String payState) 
    {
        this.payState = payState;
    }

    public String getPayState() 
    {
        return payState;
    }
    public void setApplyDate(Date applyDate) 
    {
        this.applyDate = applyDate;
    }

    public Date getApplyDate() 
    {
        return applyDate;
    }
    public void setPayDate(Date payDate) 
    {
        this.payDate = payDate;
    }

    public Date getPayDate() 
    {
        return payDate;
    }
    public void setPayTime(Date payTime) 
    {
        this.payTime = payTime;
    }

    public Date getPayTime() 
    {
        return payTime;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("userType", getUserType())
            .append("amount", getAmount())
            .append("paySerial", getPaySerial())
            .append("thirdSerial", getThirdSerial())
            .append("payState", getPayState())
            .append("remark", getRemark())
            .append("applyDate", getApplyDate())
            .append("payDate", getPayDate())
            .append("payTime", getPayTime())
            .append("operator", getOperator())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
