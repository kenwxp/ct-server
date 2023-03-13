package com.cloudtimes.business.mobile.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "返回参数")
@Data
public class GetShopDetailResp {
    @Schema(description = "门店号")
    private String shopNo;
    @Schema(description = "门店名")
    private String shopName;
    @Schema(description = "店铺详细地址")
    private String address;
    @Schema(description = "联系人姓名")
    private String contactName;
    @Schema(description = "联系方式")
    private String contactPhone;
//    @Schema(description = "合同扫描图片地址")
//    private String contract;
//    @Schema(description = "合同开始时间")
//    private String contractBeginTime;
//    @Schema(description = "合同结束时间")
//    private String contractEndTime;
    @Schema(description = "店铺状态（0-正常 1-异常）")
    private String state;
    @Schema(description = "云值守状态（0-否 1-是）")
    private String isSupervise;
}
