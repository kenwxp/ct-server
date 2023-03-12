package com.cloudtimes.station.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;

@Schema(description = "返回参数")
@Data
@Slf4j
public class JoinAudioReq {
    @Schema(description = "门店编号", required = true)
    @NotEmpty
    private String shopId;
    @Schema(description = "是否加入 0-离开 1-加入", required = true)
    @NotEmpty
    private String isJoin;
}
