package com.cloudtimes.hardwaredevice.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;

/**
 * 门店对象 ct_store
 *
 * @author tank
 * @date 2023-02-09
 */
@Data
public class CtStore extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String id;

    /**
     * 店铺名称
     */
    @Excel(name = "店铺名称")
    private String name;

    /**
     * 详细地址
     */
    @Excel(name = "详细地址")
    private String address;

    /**
     * 短地址
     */
    @Excel(name = "短地址")
    private String shortAddress;

    /**
     * 门店号
     */
    @Excel(name = "门店号")
    private String storeNo;

    /**
     * 地区号
     */
    @Excel(name = "地区号")
    private String regionCode;

    /**
     * 经度
     */
    @Excel(name = "经度")
    private BigDecimal longitude;

    /**
     * 纬度
     */
    @Excel(name = "纬度")
    private BigDecimal latitude;

    /**
     * 门头照片
     */
    @Excel(name = "门头照片")
    private String photoUrl;

    /**
     * 面积
     */
    @Excel(name = "面积")
    private String area;

    /**
     * 联系人姓名
     */
    @Excel(name = "联系人姓名")
    private String contactName;

    /**
     * 联系电话
     */
    @Excel(name = "联系电话")
    private String contactPhone;

    /**
     * 开店费用
     */
    @Excel(name = "开店费用")
    private BigDecimal saleAmount;

    /**
     * 扣减比例 单位%
     */
    @Excel(name = "扣减比例 单位%")
    private BigDecimal dividendRate;

    /**
     * 扫码有效距离 单位米
     */
    @Excel(name = "扫码有效距离 单位米")
    private BigDecimal validDistance;

    /**
     * 最大购物人数
     */
    @Excel(name = "最大购物人数")
    private Long maxBuyPerson;

    /**
     * 是否值守
     */
    @Excel(name = "是否值守")
    private String isSupervise;

    /**
     * 值守日志编号
     */
    @Excel(name = "值守日志编号")
    private String superviseLogId;

    /**
     * 开设状态
     */
    @Excel(name = "开设状态")
    private String buildState;

    /**
     * 门店状态
     */
    @Excel(name = "门店状态")
    private String state;

    /**
     * 店老板用户编号
     */
    @Excel(name = "店老板用户编号")
    private String bossId;

    /**
     * 代理用户编号
     */
    @Excel(name = "代理用户编号")
    private String agentId;

    /**
     * 门店上线日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "门店上线日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date storeOnlineDate;

    /**
     * 门店上线时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "门店上线时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date storeOnlineTime;

    /**
     * 三方商户编号
     */
    @Excel(name = "三方商户编号")
    private String merchantSn;

    /**
     * 三方门店编号
     */
    @Excel(name = "三方门店编号")
    private String storeSn;

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
