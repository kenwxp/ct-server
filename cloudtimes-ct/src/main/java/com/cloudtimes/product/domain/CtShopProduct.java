package com.cloudtimes.product.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;

/**
 * 店铺商品对象 ct_shop_product
 *
 * @author tank
 * @date 2023-02-15
 */
@Data
public class CtShopProduct extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 店铺商品编号
     */
    private String id;

    /**
     * 店铺编号
     */
    @Excel(name = "店铺编号")
    private String shopNo;

    /**
     * 商品分类编号
     */
    @Excel(name = "商品分类编号")
    private String categoryId;
    /**
     * 商品分类名（冗余）
     */
    @Excel(name = "商品分类名（冗余")
    private String categoryName;
    /**
     * 商品条形码
     */
    @Excel(name = "商品条形码")
    private String barcode;

    /**
     * 商品名称
     */
    @Excel(name = "商品名称")
    private String productName;

    /**
     * 商品英文名称
     */
    @Excel(name = "商品英文名称")
    private String englishName;

    /**
     * 品牌名称
     */
    @Excel(name = "品牌名称")
    private String brand;

    /**
     * 标签
     */
    @Excel(name = "标签")
    private String label;

    /**
     * 供货商编号
     */
    @Excel(name = "供货商编号")
    private String supplierId;

    /**
     * 供货商
     */
    @Excel(name = "供货商")
    private String supplier;

    /**
     * 规格
     */
    @Excel(name = "规格")
    private String specification;

    /**
     * 单位
     */
    @Excel(name = "单位")
    private String unit;

    /**
     * 重量/净含量
     */
    @Excel(name = "重量/净含量")
    private String weight;

    /**
     * 颜色
     */
    @Excel(name = "颜色")
    private String color;

    /**
     * 尺寸
     */
    @Excel(name = "尺寸")
    private String size;

    /**
     * 样式/款式
     */
    @Excel(name = "样式/款式")
    private String style;

    /**
     * 进货价
     */
    @Excel(name = "进货价")
    private Long purchasePrice;

    /**
     * 零售价
     */
    @Excel(name = "零售价")
    private Long retailPrice;

    /**
     * 批发价
     */
    @Excel(name = "批发价")
    private Long wholesalePrice;

    /**
     * 会员价
     */
    @Excel(name = "会员价")
    private Long vipPrice;

    /**
     * 库存
     */
    @Excel(name = "库存")
    private Long stock;

    /**
     * 库存上限
     */
    @Excel(name = "库存上限")
    private Long maxStock;

    /**
     * 库存下限
     */
    @Excel(name = "库存下限")
    private Long minStock;

    /**
     * 商品图片地址
     */
    @Excel(name = "商品图片地址")
    private String pictureUrl;

    /**
     * 生产日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date productionDate;

    /**
     * 保质期
     */
    @Excel(name = "保质期")
    private String qualityPeriod;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remarks;

    /**
     * 商品是否启用
     */
    @Excel(name = "商品是否启用")
    private String isEnable;

    /**
     * 是否启用会员
     */
    @Excel(name = "是否启用会员")
    private String isEnableVipPrice;

    /**
     * 是否积分商品
     */
    @Excel(name = "是否积分商品")
    private String isEnablePoints;

    /**
     * 是否已删除
     */
    @Excel(name = "是否已删除")
    private String isDeleted;

}
