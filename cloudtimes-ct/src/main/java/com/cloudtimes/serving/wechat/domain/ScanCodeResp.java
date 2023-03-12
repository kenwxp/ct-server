package com.cloudtimes.serving.wechat.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "返回参数")
@Data
public class ScanCodeResp {
    @Schema(description = "购物流水号")
    private String shoppingId;
    @Schema(description = "是否云值守 0-否 1-是")
    private String isSupervise;
}
