package com.cloudtimes.account.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;

/**
 * 卡劵维护对象 ct_user_cards
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
public class CtUserCards extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private String id;

    /** 活动编号 */
    @Excel(name = "活动编号")
    private String activityId;

    /** 用户编号 */
    @Excel(name = "用户编号")
    private String userId;

    /** 卡编号 */
    @Excel(name = "卡编号")
    private String cardNo;

    /** 额度 */
    @Excel(name = "额度")
    private BigDecimal totalAmount;

    /** 余额 */
    @Excel(name = "余额")
    private BigDecimal balance;

    /** 折扣比例 */
    @Excel(name = "折扣比例")
    private BigDecimal discountRatio;

    /** 折扣次数 */
    @Excel(name = "折扣次数")
    private Long discountTimes;

    /** 使用门店 */
    @Excel(name = "使用门店")
    private String storeCodes;

    /** 卡卷类型 */
    @Excel(name = "卡卷类型")
    private String cardType;

    /** 使用类型 */
    @Excel(name = "使用类型")
    private String useType;

    /** 资产类型 */
    @Excel(name = "资产类型")
    private String assetsType;

    /** 来源类型 */
    @Excel(name = "来源类型")
    private String sourceType;

    /** 期限类型 */
    @Excel(name = "期限类型")
    private String termType;

    /** 是否有时段 */
    @Excel(name = "是否有时段")
    private String hasTimeInterval;

    /** 卡劵状态 */
    @Excel(name = "卡劵状态")
    private String cardState;

    /** 时段 */
    @Excel(name = "时段")
    private String timeInterval;

    /** 有效开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 有效结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

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
    public void setCardNo(String cardNo) 
    {
        this.cardNo = cardNo;
    }

    public String getCardNo() 
    {
        return cardNo;
    }
    public void setTotalAmount(BigDecimal totalAmount) 
    {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() 
    {
        return totalAmount;
    }
    public void setBalance(BigDecimal balance) 
    {
        this.balance = balance;
    }

    public BigDecimal getBalance() 
    {
        return balance;
    }
    public void setDiscountRatio(BigDecimal discountRatio) 
    {
        this.discountRatio = discountRatio;
    }

    public BigDecimal getDiscountRatio() 
    {
        return discountRatio;
    }
    public void setDiscountTimes(Long discountTimes) 
    {
        this.discountTimes = discountTimes;
    }

    public Long getDiscountTimes() 
    {
        return discountTimes;
    }
    public void setStoreCodes(String storeCodes) 
    {
        this.storeCodes = storeCodes;
    }

    public String getStoreCodes() 
    {
        return storeCodes;
    }
    public void setCardType(String cardType) 
    {
        this.cardType = cardType;
    }

    public String getCardType() 
    {
        return cardType;
    }
    public void setUseType(String useType) 
    {
        this.useType = useType;
    }

    public String getUseType() 
    {
        return useType;
    }
    public void setAssetsType(String assetsType) 
    {
        this.assetsType = assetsType;
    }

    public String getAssetsType() 
    {
        return assetsType;
    }
    public void setSourceType(String sourceType) 
    {
        this.sourceType = sourceType;
    }

    public String getSourceType() 
    {
        return sourceType;
    }
    public void setTermType(String termType) 
    {
        this.termType = termType;
    }

    public String getTermType() 
    {
        return termType;
    }
    public void setHasTimeInterval(String hasTimeInterval) 
    {
        this.hasTimeInterval = hasTimeInterval;
    }

    public String getHasTimeInterval() 
    {
        return hasTimeInterval;
    }
    public void setCardState(String cardState) 
    {
        this.cardState = cardState;
    }

    public String getCardState() 
    {
        return cardState;
    }
    public void setTimeInterval(String timeInterval) 
    {
        this.timeInterval = timeInterval;
    }

    public String getTimeInterval() 
    {
        return timeInterval;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
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
            .append("activityId", getActivityId())
            .append("userId", getUserId())
            .append("cardNo", getCardNo())
            .append("totalAmount", getTotalAmount())
            .append("balance", getBalance())
            .append("discountRatio", getDiscountRatio())
            .append("discountTimes", getDiscountTimes())
            .append("storeCodes", getStoreCodes())
            .append("cardType", getCardType())
            .append("useType", getUseType())
            .append("assetsType", getAssetsType())
            .append("sourceType", getSourceType())
            .append("termType", getTermType())
            .append("hasTimeInterval", getHasTimeInterval())
            .append("cardState", getCardState())
            .append("timeInterval", getTimeInterval())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("delFlag", getDelFlag())
            .append("remark", getRemark())
            .append("createDate", getCreateDate())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
