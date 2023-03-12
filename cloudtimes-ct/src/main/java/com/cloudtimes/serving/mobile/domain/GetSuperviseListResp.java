package com.cloudtimes.serving.mobile.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "返回参数")
@Data
public class GetSuperviseListResp {
    @Schema(description = "值守流水号")
    private String superviseId;
    @Schema(description = "门店id")
    private String shopId;
    @Schema(description = "门店名")
    private String shopName;
    @Schema(description = "门店地址")
    private String shopAddress;
    @Schema(description = "值守状态 0-值守中 1-已结束")
    private String state;
    @Schema(description = "开始时间")
    private String beginTime;
    @Schema(description = "结束时间")
    private String endTime;
}
