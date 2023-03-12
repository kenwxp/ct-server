package com.cloudtimes.app.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class WsOrderDetailData {
    @Schema(description = "物品编号")
    private String itemId;
    @Schema(description = "物品名称")
    private String itemName;
    @Schema(description = "物品类别")
    private String itemTypeId;
    @Schema(description = "类别名称")
    private String itemTypeName;
    @Schema(description = "物品数量")
    private String itemCount;
    @Schema(description = "物品单价")
    private String itemPrice;
    @Schema(description = "物品进货价")
    private String itemPrimePrice;
    @Schema(description = "小计")
    private String itemSum;
}
