package com.cloudtimes.business.mobile.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Schema(description = "请求参数")
@Data
@Slf4j
public class GetDeviceListReq {
    @NotEmpty
    @Schema(description = "店名id")
    private String shopId;
    @NotNull
    @Schema(description = "查询类型 0-全设备列表 1-播放链接列表")
    private int queryType;
}
