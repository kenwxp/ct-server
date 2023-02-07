package com.cloudtimes.supervise.domain;

import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 事件对象 ct_events
 *
 * @author wangxp
 * @date 2023-02-07
 */
@Data
@Slf4j
public class CtEvents extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String id;

    /**
     * 事件名称
     */
    @Excel(name = "事件名称")
    private String eventName;

    /**
     * 事件类型
     */
    @Excel(name = "事件类型")
    private String eventType;

    /**
     * 事件详情
     */
    @Excel(name = "事件详情")
    private String context;

    /**
     * 用户编号
     */
    @Excel(name = "用户编号")
    private String userId;

    /**
     * 用户昵称
     */
    @Excel(name = "用户昵称")
    private String nickName;

    /**
     * 任务编号
     */
    @Excel(name = "任务编号")
    private String taskId;

    /**
     * 购物编号
     */
    @Excel(name = "购物编号")
    private String shoppingId;

    /**
     * 用户类型
     */
    @Excel(name = "用户类型")
    private String userType;

    /**
     * 应用端类型
     */
    @Excel(name = "应用端类型")
    private String sourceType;

    /**
     * 是否结束
     */
    @Excel(name = "是否结束")
    private String enabled;

    /**
     * 是否删除
     */
    private String delFlag;

    /**
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

}
