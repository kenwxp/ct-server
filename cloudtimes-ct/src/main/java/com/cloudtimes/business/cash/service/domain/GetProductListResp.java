package com.cloudtimes.business.cash.service.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Schema(description = "返回参数")
@Data
@Slf4j
public class GetProductListResp {
    @Schema(description = "商品的唯一标识", required = true)
    private String productUid;
    @Schema(description = "商品名称", required = true)
    private String productName;
    @Schema(description = "商品分类编码", required = true)
    private String categoryCode;
    @Schema(description = "商品条形码", required = true)
    private String barcode;
    @Schema(description = "商品图片的url", required = true)
    private String imageUrl;
    @Schema(description = "商品进货价", required = true)
    private int buyPrice;
    @Schema(description = "商品销售价", required = true)
    private int sellPrice;
}
