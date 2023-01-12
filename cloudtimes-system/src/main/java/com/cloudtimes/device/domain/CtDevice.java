package com.cloudtimes.device.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;

/**
 * 电子设备管理对象 ct_device
 * 
 * @author tank
 * @date 2023-01-12
 */
public class CtDevice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String name;

    /** 门店编码 */
    @Excel(name = "门店编码")
    private String storeCode;

    /** 设备编码 */
    @Excel(name = "设备编码")
    private String deviceCode;

    /** 设备类型 */
    @Excel(name = "设备类型")
    private Long deviceType;

    /** 设备型号 */
    @Excel(name = "设备型号")
    private String deviceModel;

    /** 品牌名称 */
    @Excel(name = "品牌名称")
    private String brandName;

    /** 设备序列号 */
    @Excel(name = "设备序列号")
    private String deviceSerial;

    /** 设备位置 */
    @Excel(name = "设备位置")
    private Long devicePosition;

    /** 设备验证码 */
    @Excel(name = "设备验证码")
    private String validateCode;

    /** 设备归属 */
    @Excel(name = "设备归属")
    private Long deviceAscription;

    /** 状态 */
    @Excel(name = "状态")
    private Long state;

    /** 最近上云时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最近上云时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastOnlineTime;

    /** 最近下云时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最近下云时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastOffineTime;

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
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
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
    public void setDeviceType(Long deviceType) 
    {
        this.deviceType = deviceType;
    }

    public Long getDeviceType() 
    {
        return deviceType;
    }
    public void setDeviceModel(String deviceModel) 
    {
        this.deviceModel = deviceModel;
    }

    public String getDeviceModel() 
    {
        return deviceModel;
    }
    public void setBrandName(String brandName) 
    {
        this.brandName = brandName;
    }

    public String getBrandName() 
    {
        return brandName;
    }
    public void setDeviceSerial(String deviceSerial) 
    {
        this.deviceSerial = deviceSerial;
    }

    public String getDeviceSerial() 
    {
        return deviceSerial;
    }
    public void setDevicePosition(Long devicePosition) 
    {
        this.devicePosition = devicePosition;
    }

    public Long getDevicePosition() 
    {
        return devicePosition;
    }
    public void setValidateCode(String validateCode) 
    {
        this.validateCode = validateCode;
    }

    public String getValidateCode() 
    {
        return validateCode;
    }
    public void setDeviceAscription(Long deviceAscription) 
    {
        this.deviceAscription = deviceAscription;
    }

    public Long getDeviceAscription() 
    {
        return deviceAscription;
    }
    public void setState(Long state) 
    {
        this.state = state;
    }

    public Long getState() 
    {
        return state;
    }
    public void setLastOnlineTime(Date lastOnlineTime) 
    {
        this.lastOnlineTime = lastOnlineTime;
    }

    public Date getLastOnlineTime() 
    {
        return lastOnlineTime;
    }
    public void setLastOffineTime(Date lastOffineTime) 
    {
        this.lastOffineTime = lastOffineTime;
    }

    public Date getLastOffineTime() 
    {
        return lastOffineTime;
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
            .append("name", getName())
            .append("storeCode", getStoreCode())
            .append("deviceCode", getDeviceCode())
            .append("deviceType", getDeviceType())
            .append("deviceModel", getDeviceModel())
            .append("brandName", getBrandName())
            .append("deviceSerial", getDeviceSerial())
            .append("devicePosition", getDevicePosition())
            .append("validateCode", getValidateCode())
            .append("deviceAscription", getDeviceAscription())
            .append("state", getState())
            .append("lastOnlineTime", getLastOnlineTime())
            .append("lastOffineTime", getLastOffineTime())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
