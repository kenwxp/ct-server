package com.cloudtimes.hardwaredevice.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;

/**
 * 申请值守日志对象 ct_supervise_logs
 *
 * @author tank
 * @date 2023-02-08
 */
@Slf4j
@Data
public class CtSuperviseLogs extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String id;

    /**
     * 申请人
     */
    @Excel(name = "申请人")
    private String userId;

    /**
     * 店铺编号
     */
    @Excel(name = "店铺编号")
    private String storeId;
    /**
     * 店铺名
     */
    @Excel(name = "门店名")
    private String storeName;

    /**
     * 门店地址
     */
    @Excel(name = "门店地址")
    private String storeAddress;
    /**
     * 值守开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "值守开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 值守结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "值守结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 是否删除
     */
    private String delFlag;

}
