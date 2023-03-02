package com.cloudtimes.serving.mobile.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(description = "返回参数")
@Data
public class GetDeviceListResp {
    @ApiModelProperty("设备名")
    private String deviceName;
    @ApiModelProperty("设备序列号")
    private String deviceSerial;
    @ApiModelProperty("设备位置")
    private String devicePosition;
    @ApiModelProperty("设备状态")
    private String deviceStatus;
    @ApiModelProperty("状态更新时间")
    private String updateTime;
    @ApiModelProperty("视频播放token")
    private String token;
    @ApiModelProperty("视频链接")
    private String videoUrl;
    @ApiModelProperty("当前截图")
    private String imageUrl;
}
