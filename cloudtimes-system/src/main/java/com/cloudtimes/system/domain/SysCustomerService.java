package com.cloudtimes.system.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;

/**
 * 客服特性参数对象 sys_customer_service
 *
 * @author wangxp
 * @date 2023-03-02
 */
@Data
public class SysCustomerService extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String id;

    /**
     * 客服编号
     */
    @Excel(name = "客服编号")
    private String staffId;

    /**
     * 客服名
     */
    @Excel(name = "客服名")
    private String staffName;

    /**
     * 客服级别
     */
    @Excel(name = "客服级别")
    private String level;

    /**
     * 最大接受任务量
     */
    @Excel(name = "最大接受任务量")
    private Long maxAcceptTask;

    /**
     * 最大接受订单量
     */
    @Excel(name = "最大接受订单量")
    private Long maxAcceptOrder;

    /**
     * 是否删除
     */
    private String delFlag;

}
