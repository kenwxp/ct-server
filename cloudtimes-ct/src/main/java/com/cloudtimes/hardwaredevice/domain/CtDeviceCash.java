package com.cloudtimes.hardwaredevice.domain;

import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 收银机特有信息对象 ct_device_cash
 *
 * @author tank
 * @date 2023-01-17
 */
public class CtDeviceCash extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 序列号
     */
    private String id;


    /**
     * 终端ID
     */
    @Excel(name = "终端ID")
    private String deviceNo;

    /**
     * 终端SN
     */
    @Excel(name = "终端SN")
    private String terminalSn;

    /**
     * 终端密钥
     */
    @Excel(name = "终端密钥")
    private String terminalKey;

    /**
     * 激活码
     */
    @Excel(name = "激活码")
    private String activateId;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * 支付渠道编码
     */
    @Excel(name = "支付渠道编码")
    private String paymentId;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private String state;

    /**
     * 是否删除
     */
    private Long delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setTerminalSn(String terminalSn) {
        this.terminalSn = terminalSn;
    }

    public String getTerminalSn() {
        return terminalSn;
    }

    public void setTerminalKey(String terminalKey) {
        this.terminalKey = terminalKey;
    }

    public String getTerminalKey() {
        return terminalKey;
    }

    public String getActivateId() {
        return activateId;
    }

    public void setActivateId(String activateId) {
        this.activateId = activateId;
    }


    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setDelFlag(Long delFlag) {
        this.delFlag = delFlag;
    }

    public Long getDelFlag() {
        return delFlag;
    }



    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("deviceNo", getDeviceNo())
                .append("terminalSn", getTerminalSn())
                .append("terminalKey", getTerminalKey())
                .append("state", getState())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
