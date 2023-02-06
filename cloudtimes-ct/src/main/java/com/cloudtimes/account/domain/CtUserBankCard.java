package com.cloudtimes.account.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;

/**
 * 用户银行卡对象 ct_user_bank_card
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
public class CtUserBankCard extends BaseEntity
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

    /** 开户行名称 */
    @Excel(name = "开户行名称")
    private String openBankName;

    /** 持卡人姓名 */
    @Excel(name = "持卡人姓名")
    private String userName;

    /** 预留手机号 */
    @Excel(name = "预留手机号")
    private String mobile;

    /** 卡号 */
    @Excel(name = "卡号")
    private String cardNo;

    /** 是否解绑 */
    @Excel(name = "是否解绑")
    private String isUnbind;

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
    public void setUserType(String userType) 
    {
        this.userType = userType;
    }

    public String getUserType() 
    {
        return userType;
    }
    public void setOpenBankName(String openBankName) 
    {
        this.openBankName = openBankName;
    }

    public String getOpenBankName() 
    {
        return openBankName;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setCardNo(String cardNo) 
    {
        this.cardNo = cardNo;
    }

    public String getCardNo() 
    {
        return cardNo;
    }
    public void setIsUnbind(String isUnbind) 
    {
        this.isUnbind = isUnbind;
    }

    public String getIsUnbind() 
    {
        return isUnbind;
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
            .append("userType", getUserType())
            .append("openBankName", getOpenBankName())
            .append("userName", getUserName())
            .append("mobile", getMobile())
            .append("cardNo", getCardNo())
            .append("remark", getRemark())
            .append("isUnbind", getIsUnbind())
            .append("delFlag", getDelFlag())
            .append("createDate", getCreateDate())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
