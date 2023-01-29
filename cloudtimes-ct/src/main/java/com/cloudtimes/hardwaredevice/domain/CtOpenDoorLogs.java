package com.cloudtimes.hardwaredevice.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;

/**
 * 开门日志对象 ct_open_door_logs
 * 
 * @author tank
 * @date 2023-01-17
 */
public class CtOpenDoorLogs extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序列号 */
    private Long id;

    /** 日志编码 */
    @Excel(name = "日志编码")
    private String logCode;

    /** 店铺编码 */
    @Excel(name = "店铺编码")
    private String storeCode;

    /** 设备编码 */
    @Excel(name = "设备编码")
    private String deviceCode;

    /** 会员编码 */
    @Excel(name = "会员编码")
    private String userCode;

    /** 操作渠道 */
    @Excel(name = "操作渠道")
    private String optChannel;

    /** 操作类型 */
    @Excel(name = "操作类型")
    private String optType;

    /** 状态 */
    @Excel(name = "状态")
    private String state;

    /** 是否删除 */
    private Long delFlag;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date date;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setLogCode(String logCode) 
    {
        this.logCode = logCode;
    }

    public String getLogCode() 
    {
        return logCode;
    }
    public void setStoreCode(String storeCode) 
    {
        this.storeCode = storeCode;
    }

    public String getStoreCode() 
    {
        return storeCode;
    }
    public void setDeviceCode(String deviceCode) 
    {
        this.deviceCode = deviceCode;
    }

    public String getDeviceCode() 
    {
        return deviceCode;
    }
    public void setUserCode(String userCode) 
    {
        this.userCode = userCode;
    }

    public String getUserCode() 
    {
        return userCode;
    }
    public void setOptChannel(String optChannel) 
    {
        this.optChannel = optChannel;
    }

    public String getOptChannel() 
    {
        return optChannel;
    }
    public void setOptType(String optType) 
    {
        this.optType = optType;
    }

    public String getOptType() 
    {
        return optType;
    }
    public void setState(String state) 
    {
        this.state = state;
    }

    public String getState() 
    {
        return state;
    }
    public void setDelFlag(Long delFlag) 
    {
        this.delFlag = delFlag;
    }

    public Long getDelFlag() 
    {
        return delFlag;
    }
    public void setDate(Date date) 
    {
        this.date = date;
    }

    public Date getDate() 
    {
        return date;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("logCode", getLogCode())
            .append("storeCode", getStoreCode())
            .append("deviceCode", getDeviceCode())
            .append("userCode", getUserCode())
            .append("optChannel", getOptChannel())
            .append("optType", getOptType())
            .append("state", getState())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("date", getDate())
            .toString();
    }
}
