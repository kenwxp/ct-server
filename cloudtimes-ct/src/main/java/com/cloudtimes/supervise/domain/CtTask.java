package com.cloudtimes.supervise.domain;

import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 值守任务对象 ct_task
 *
 * @author wangxp
 * @date 2023-02-07
 */
@Data
@Slf4j
public class CtTask extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String id;

    /**
     * 门店号
     */
    @Excel(name = "门店编号")
    private String storeId;

    /**
     * 值守员工号
     */
    @Excel(name = "值守员工号")
    private String staffCode;

    /**
     * 任务状态
     * 进行中	0
     * 已结束	1
     */
    @Excel(name = "任务状态")
    private String taskType;

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
     * 有异常	1
     */
    @Excel(name = "是否异常")
    private String isExceptional;

    /**
     * 开门日志编号
     */
    @Excel(name = "开门日志编号")
    private String doorLogId;

    /**
     * 值守区域
     */
    @Excel(name = "值守区域")
    private String supeviseArea;

    /**
     * 状态
     * 进行中	0
     * 已结束	1
     */
    @Excel(name = "状态")
    private String state;

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
