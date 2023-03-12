package com.cloudtimes.station.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "请求参数")
@Data
public class GetLocalVideoReq {
    @Schema(description = "设备编号", required = true)
    private String deviceId;// 监控设备序列号
    @Schema(description = "开始时间 yyyy-mm-dd hh:mm:ss", required = true)
    private String beginTime;// 开始时间
    @Schema(description = "结束时间 yyyy-mm-dd hh:mm:ss", required = true)
    private String endTime;// 结束时间
}
