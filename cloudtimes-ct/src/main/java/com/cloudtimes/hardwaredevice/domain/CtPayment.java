package com.cloudtimes.hardwaredevice.domain;

import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 支付渠道对象 ct_payment
 *
 * @author tank
 * @date 2023-02-22
 */
@Data
@Slf4j
public class CtPayment extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String id;

    /**
     * 收款主体id
     */
    @Excel(name = "收款主体id")
    private String payeeId;

    /**
     * 收款主体类型  1-收银机 2-门店 3-店老板 4-会员
     */
    @Excel(name = "收款主体类型")
    private String payeeType;

    /**
     * 支付渠道类型 0-支付宝 1-微信 2-收钱吧 3-汇天下
     */
    @Excel(name = "支付渠道类型")
    private String payWay;

    /**
     * 渠道参数
     */
    @Excel(name = "渠道参数")
    private String payParams;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String payDesc;

    /**
     * 是否启用
     */
    @Excel(name = "是否启用")
    private String enabled;

    /**
     * 是否删除
     */
    private String delFlag;
}
