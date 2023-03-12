package com.cloudtimes.serving.mobile.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "返回参数")
@Data
public class GetDeviceListResp {
    @Schema(description = "设备名")
    private String deviceName;
    @Schema(description = "设备序列号")
    private String deviceSerial;
    @Schema(description = "设备位置")
    private String devicePosition;
    @Schema(description = "设备状态")
    private String deviceStatus;
    @Schema(description = "状态更新时间")
    private String updateTime;
    @Schema(description = "视频播放token")
    private String token;
    @Schema(description = "视频链接")
    private String videoUrl;
    @Schema(description = "当前截图")
    private String imageUrl;
}
