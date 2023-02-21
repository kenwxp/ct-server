package com.cloudtimes.supervise.domain;

import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 购物对象 ct_shopping
 *
 * @author wangxp
 * @date 2023-02-07
 */
@Data
@Slf4j
public class CtShopping extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String id;

    /**
     * 用户编号
     */
    @Excel(name = "用户编号")
    private String userId;

    /**
     * 任务编号
     */
    @Excel(name = "任务编号")
    private String taskId;

    /**
     * 门店编号
     */
    @Excel(name = "门店编号")
    private String storeId;

    /**
     * 值守员工号
     */
    @Excel(name = "值守员工号")
    private String staffCode;

    /**
     * 购物状态
     * 购物中	0
     * 无购物	1
     * 购物结束	2
     */
    @Excel(name = "购物状态")
    private String state;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String descText;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 是否异常
     * 无异常	0
     * 偷盗异常	1
     * 已追回	2
     */
    @Excel(name = "是否异常")
    private String exceptionalState;

    /**
     * 客服审核
     * 无异常	0
     * 偷盗异常	1
     * 已追回	2
     */
    @Excel(name = "客服审核")
    private String isApprove;

    /**
     * 负责人审核
     * 无异常	0
     * 偷盗异常	1
     * 已追回	2
     */
    @Excel(name = "负责人审核")
    private String isLeadApprove;

    /**
     * 店主审核
     * 无异常	0
     * 偷盗异常	1
     * 已追回	2
     */
    @Excel(name = "店主审核")
    private String isBossApprove;

    /**
     * 是否删除
     */
    private String delFlag;
    /**
     * 创建日期
     */

    @Excel(name = "创建时间")
    private Date createTime;
    /**
     * 创建日期
     */
    @Excel(name = "修改时间")
    private Date updateTime;
}
