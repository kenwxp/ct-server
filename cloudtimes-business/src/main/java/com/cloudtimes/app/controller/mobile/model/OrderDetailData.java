package com.cloudtimes.app.controller.mobile.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@ApiModel(description = "返回参数")
@Data
public class OrderDetailData {
    @ApiModelProperty("物品编号")
    private String itemId;
    @ApiModelProperty("物品名称")
    private String itemName;
    @ApiModelProperty("物品类别")
    private String itemTypeId;
    @ApiModelProperty("类别名称")
    private String itemTypeName;
    @ApiModelProperty("物品数量")
    private BigDecimal itemCount;
    @ApiModelProperty("物品单价")
    private BigDecimal itemPrice;
    @ApiModelProperty("物品进货价")
    private BigDecimal itemPrimePrice;
    @ApiModelProperty("小计")
    private BigDecimal itemSum;
}
