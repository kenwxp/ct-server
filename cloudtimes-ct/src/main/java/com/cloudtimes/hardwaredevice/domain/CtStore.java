package com.cloudtimes.hardwaredevice.domain;

import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 门店对象 ct_store
 *
 * @author tank
 * @date 2023-01-17
 */
@Data
@Slf4j
public class CtStore extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 序列号
     */
    private String id;

    /**
     * 店铺名称
     */
    @Excel(name = "店铺名称")
    private String name;

    /**
     * 商户ID
     */
    @Excel(name = "商户ID")
    private String merchantId;

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
    private Long longitude;

    /**
     * 纬度
     */
    @Excel(name = "纬度")
    private Long latitude;

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
    private Long saleAmount;

    /**
     * 最大购物人数
     */
    @Excel(name = "最大购物人数")
    private Long maxBuyPerson;

    /**
     * 是否值守
     */
    @Excel(name = "是否值守")
    private Long isSupervise;

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
     * 代理用户Id
     */
    @Excel(name = "代理用户Id")
    private String agentId;

    /**
     * 门店上线日期
     */
    @Excel(name = "门店上线日期")
    private Date storeOnlineDate;

    /**
     * 门店上线时间
     */
    @Excel(name = "门店上线时间")
    private Date storeOnlineTime;

    /**
     * 是否删除
     */
    @Excel(name = "是否删除")
    private Long delFlag;

    /**
     * 创建日期
     */
    @Excel(name = "创建日期")
    private Date createDate;
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
