package com.cloudtimes.supervise.domain;

import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 支付渠道对象 ct_payment
 *
 * @author wangxp
 * @date 2023-02-07
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
     * 支付流水号
     */
    @Excel(name = "支付流水号")
    private String paymentCode;

    /**
     * 门店号
     */
    @Excel(name = "门店号")
    private String storeNo;

    /**
     * 商户号
     */
    @Excel(name = "商户号")
    private String merchantId;

    /**
     * 渠道参数
     */
    @Excel(name = "渠道参数")
    private String paramsJson;

    /**
     * 渠道图标地址
     */
    @Excel(name = "渠道图标地址")
    private String photoUrl;

    /**
     * 支付类型
     */
    @Excel(name = "支付类型")
    private String paymentType;

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
