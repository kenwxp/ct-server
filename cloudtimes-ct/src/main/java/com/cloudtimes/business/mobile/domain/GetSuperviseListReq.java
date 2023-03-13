package com.cloudtimes.business.mobile.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;


@Schema(description = "请求参数")
@Data
@Slf4j
public class GetSuperviseListReq {
    @NotNull
    @Schema(description = "页码")
    private int pageNum;
    @NotNull
    @Schema(description = "每页条数")
    private int pageSize;
}
