package com.cloudtimes.hardwaredevice.domain;

import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 开门日志对象 ct_open_door_logs
 *
 * @author tank
 * @date 2023-02-08
 */
@Data
@Slf4j
public class CtOpenDoorLogs extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String id;

    /**
     * 店铺编号
     */
    @Excel(name = "店铺编号")
    private String storeId;

    /**
     * 设备编号
     */
    @Excel(name = "设备编号")
    private String deviceId;

    /**
     * 会员编号
     */
    @Excel(name = "会员编号")
    private String memberId;

    /**
     * 操作渠道
     */
    @Excel(name = "操作渠道")
    private String optChannel;

    /**
     * 操作类型
     * "0" 交易开门
     * "1" 应急开门
     * "2" 设置密码
     * "3" 触发开门
     * "4" 店家开门
     * "5" 强锁
     * "6" 解锁
     */
    @Excel(name = "操作类型")
    private String optType;

    /**
     * 状态
     * 成功	0
     * 失败	1
     * 异常	2
     */
    @Excel(name = "状态")
    private String state;

    /**
     * 是否删除
     */
    private String delFlag;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date date;
}
