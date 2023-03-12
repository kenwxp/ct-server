package com.cloudtimes.serving.mobile.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Schema(description = "请求参数")
@Data
@Slf4j
public class GetOrderListReq {
    @Schema(description = "页码", required = true)
    private int pageNum;
    @Schema(description = "每页条数", required = true)
    private int pageSize;
    @Schema(description = "门店id", required = true)
    private String shopId;
    @Schema(description = "支付类型 0-支付宝 1-微信 2-网银 空-全部 ")
    private String payType;
    @Schema(description = "订单类型 1-值守 2-非值守 空-全部")
    private String orderType;
    @Schema(description = "时间段类型（0-自定义 1-今天，2-昨天，3-本周，4-本月", required = true)
    private String periodType;
    @Schema(description = "开始时间 periodType=0时 必填 格式：yyyy-mm-dd")
    private String beginTime;
    @Schema(description = "结束时间 periodType=0时 必填 格式：yyyy-mm-dd")
    private String endTime;
}
