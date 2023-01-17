package com.cloudtimes.hardwaredevice.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;

/**
 * 收银机特有信息对象 ct_device_cash
 * 
 * @author tank
 * @date 2023-01-17
 */
public class CtDeviceCash extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序列号 */
    private Long id;

    /** 设备编码 */
    @Excel(name = "设备编码")
    private String deviceCode;

    /** 终端ID */
    @Excel(name = "终端ID")
    private String deviceNo;

    /** 终端SN */
    @Excel(name = "终端SN")
    private String terminalSn;

    /** 终端密钥 */
    @Excel(name = "终端密钥")
    private String terminalKey;

    /** 激活码 */
    @Excel(name = "激活码")
    private String activateCode;

    /** 支付渠道编码 */
    @Excel(name = "支付渠道编码")
    private String paymentCode;

    /** 状态 */
    @Excel(name = "状态")
    private String state;

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
    public void setDeviceNo(String deviceNo) 
    {
        this.deviceNo = deviceNo;
    }

    public String getDeviceNo() 
    {
        return deviceNo;
    }
    public void setTerminalSn(String terminalSn) 
    {
        this.terminalSn = terminalSn;
    }

    public String getTerminalSn() 
    {
        return terminalSn;
    }
    public void setTerminalKey(String terminalKey) 
    {
        this.terminalKey = terminalKey;
    }

    public String getTerminalKey() 
    {
        return terminalKey;
    }
    public void setActivateCode(String activateCode) 
    {
        this.activateCode = activateCode;
    }

    public String getActivateCode() 
    {
        return activateCode;
    }
    public void setPaymentCode(String paymentCode) 
    {
        this.paymentCode = paymentCode;
    }

    public String getPaymentCode() 
    {
        return paymentCode;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deviceCode", getDeviceCode())
            .append("deviceNo", getDeviceNo())
            .append("terminalSn", getTerminalSn())
            .append("terminalKey", getTerminalKey())
            .append("activateCode", getActivateCode())
            .append("paymentCode", getPaymentCode())
            .append("state", getState())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
