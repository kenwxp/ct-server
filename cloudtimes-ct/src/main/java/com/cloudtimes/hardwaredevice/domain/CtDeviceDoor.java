package com.cloudtimes.hardwaredevice.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;

/**
 * 门禁设备密码对象 ct_device_door
 * 
 * @author tank
 * @date 2023-01-17
 */
public class CtDeviceDoor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序列号 */
    private Long id;

    /** 设备编码 */
    @Excel(name = "设备编码")
    private String deviceCode;

    /** 门禁号 */
    @Excel(name = "门禁号")
    private Long doorNo;

    /** 门禁密码 */
    @Excel(name = "门禁密码")
    private String accessPassword;

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
    public void setDeviceCode(String deviceCode) 
    {
        this.deviceCode = deviceCode;
    }

    public String getDeviceCode() 
    {
        return deviceCode;
    }
    public void setDoorNo(Long doorNo) 
    {
        this.doorNo = doorNo;
    }

    public Long getDoorNo() 
    {
        return doorNo;
    }
    public void setAccessPassword(String accessPassword) 
    {
        this.accessPassword = accessPassword;
    }

    public String getAccessPassword() 
    {
        return accessPassword;
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
            .append("deviceCode", getDeviceCode())
            .append("doorNo", getDoorNo())
            .append("accessPassword", getAccessPassword())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
