package com.cloudtimes.hardwaredevice.domain;

import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 收银机特有信息对象 ct_device_cash
 *
 * @author tank
 * @date 2023-01-17
 */
@Data
public class CtDeviceCash extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 序列号
     */
    private String id;


    /**
     * 终端ID
     */
    @Excel(name = "终端NO")
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
    private String activateCode;

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

}
