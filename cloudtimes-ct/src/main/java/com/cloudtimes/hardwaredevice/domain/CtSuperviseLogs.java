package com.cloudtimes.hardwaredevice.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;

/**
 * 申请值守日志对象 ct_supervise_logs
 * 
 * @author tank
 * @date 2023-01-17
 */
public class CtSuperviseLogs extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序列号 */
    private Long id;

    /** 日志编码 */
    @Excel(name = "日志编码")
    private String superviseCode;

    /** 申请人 */
    @Excel(name = "申请人")
    private String userCode;

    /** 门店编码 */
    @Excel(name = "门店编码")
    private String storeCode;

    /** 值守开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "值守开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 值守结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "值守结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 是否删除 */
    private Long delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSuperviseCode(String superviseCode) 
    {
        this.superviseCode = superviseCode;
    }

    public String getSuperviseCode() 
    {
        return superviseCode;
    }
    public void setUserCode(String userCode) 
    {
        this.userCode = userCode;
    }

    public String getUserCode() 
    {
        return userCode;
    }
    public void setStoreCode(String storeCode) 
    {
        this.storeCode = storeCode;
    }

    public String getStoreCode() 
    {
        return storeCode;
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
    public void setDelFlag(Long delFlag) 
    {
        this.delFlag = delFlag;
    }

    public Long getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("superviseCode", getSuperviseCode())
            .append("userCode", getUserCode())
            .append("storeCode", getStoreCode())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
