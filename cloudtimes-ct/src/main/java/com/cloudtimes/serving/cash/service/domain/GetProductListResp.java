package com.cloudtimes.serving.cash.service.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ApiModel(description = "返回参数")
@Data
@Slf4j
public class GetProductListResp {
    @ApiModelProperty(value = "商品的唯一标识", required = true)
    private String productUid;
    @ApiModelProperty(value = "商品名称", required = true)
    private String productName;
    @ApiModelProperty(value = "商品分类编码", required = true)
    private String categoryCode;
    @ApiModelProperty(value = "商品条形码", required = true)
    private String barcode;
    @ApiModelProperty(value = "商品图片的url", required = true)
    private String imageUrl;
    @ApiModelProperty(value = "商品进货价", required = true)
    private int buyPrice;
    @ApiModelProperty(value = "商品销售价", required = true)
    private int sellPrice;
}
