package com.cloudtimes.system.domain;

import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 客服特性参数对象 sys_customer_service
 *
 * @author wangxp
 * @date 2023-03-03
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
    private Long serviceId;

    /**
     * 客服名
     */
    @Excel(name = "客服名")
    private String serviceName;

    /**
     * 客服负责人编号
     */
    @Excel(name = "客服负责人编号")
    private Long superiorId;

    /**
     * 客服负责人名
     */
    @Excel(name = "客服负责人名")
    private String superiorName;

    /**
     * 部门id
     */
    @Excel(name = "部门id")
    private Long deptId;

    /**
     * 部门名
     */
    @Excel(name = "部门名")
    private String deptName;

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
