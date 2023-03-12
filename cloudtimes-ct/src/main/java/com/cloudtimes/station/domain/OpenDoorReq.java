package com.cloudtimes.station.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;

@Schema(description = "返回参数")
@Data
@Slf4j
public class OpenDoorReq {
    @NotEmpty
    @Schema(description = "门店编号", required = true)
    private String shopId;
}
