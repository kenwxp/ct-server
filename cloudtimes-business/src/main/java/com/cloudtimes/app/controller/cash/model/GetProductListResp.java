package com.cloudtimes.app.controller.cash.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "GetProductListResp", description = "获取商品列表返回列表项")
@Data
@Slf4j
public class GetProductListResp {
    private String productUid;         // 商品的唯一标识
    private String productName;        // 商品名称
    private String categoryUid;         // 商品类别的唯一标识
    private String categoryName;        // 商品类别名称
    private String barcode;            // 商品条形码
    private String imageUrl;           // 商品图片的url
    private int buyPrice;           // 商品进货价
    private int sellPrice;          // 商品销售价
    private int customerPrice;      // 商品会员价
    private int isCustomerDiscount; // 是否开启会员折扣支持，0-否，1-是，
}
