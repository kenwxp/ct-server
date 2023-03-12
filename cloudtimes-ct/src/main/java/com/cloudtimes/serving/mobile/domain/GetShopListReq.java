package com.cloudtimes.serving.mobile.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Schema(description = "请求参数")
@Data
@Slf4j
public class GetShopListReq {
    @Schema(description = "店名")
    private String shopName; // 店名
    @Schema(description = "是否云值守 0-否 1-是")
    private String isSupervise;      // 是否云值守
}
