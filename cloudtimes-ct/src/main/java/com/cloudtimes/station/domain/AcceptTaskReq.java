package com.cloudtimes.station.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Schema(description = "返回参数")
@Data
@Slf4j
public class AcceptTaskReq {
    @Schema(description = "接单标志（0-开始接单 1-暂停接单 2-结束接单）", required = true)
    private String option;
}
