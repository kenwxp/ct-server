package com.cloudtimes.supervise.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;

/**
 * 值守任务对象 ct_task
 * 
 * @author tank
 * @date 2023-01-18
 */
public class CtTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序列号 */
    private Long id;

    /** 任务流水号 */
    @Excel(name = "任务流水号")
    private String taskCode;

    /** 门店号 */
    @Excel(name = "门店号")
    private String storeNo;

    /** 值守员工号 */
    @Excel(name = "值守员工号")
    private String staffCode;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 是否异常 */
    @Excel(name = "是否异常")
    private String isExceptional;

    /** 开门日志ID */
    @Excel(name = "开门日志ID")
    private String doorLogCode;

    /** 值守区域 */
    @Excel(name = "值守区域")
    private String supeviseArea;

    /** 状态 */
    @Excel(name = "状态")
    private String state;

    /** 是否删除 */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTaskCode(String taskCode) 
    {
        this.taskCode = taskCode;
    }

    public String getTaskCode() 
    {
        return taskCode;
    }
    public void setStoreNo(String storeNo) 
    {
        this.storeNo = storeNo;
    }

    public String getStoreNo() 
    {
        return storeNo;
    }
    public void setStaffCode(String staffCode) 
    {
        this.staffCode = staffCode;
    }

    public String getStaffCode() 
    {
        return staffCode;
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
    public void setIsExceptional(String isExceptional) 
    {
        this.isExceptional = isExceptional;
    }

    public String getIsExceptional() 
    {
        return isExceptional;
    }
    public void setDoorLogCode(String doorLogCode) 
    {
        this.doorLogCode = doorLogCode;
    }

    public String getDoorLogCode() 
    {
        return doorLogCode;
    }
    public void setSupeviseArea(String supeviseArea) 
    {
        this.supeviseArea = supeviseArea;
    }

    public String getSupeviseArea() 
    {
        return supeviseArea;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskCode", getTaskCode())
            .append("storeNo", getStoreNo())
            .append("staffCode", getStaffCode())
            .append("remark", getRemark())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("isExceptional", getIsExceptional())
            .append("doorLogCode", getDoorLogCode())
            .append("supeviseArea", getSupeviseArea())
            .append("state", getState())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
