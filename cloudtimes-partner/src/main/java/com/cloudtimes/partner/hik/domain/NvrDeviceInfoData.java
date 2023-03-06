package com.cloudtimes.partner.hik.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL) //null的字段不处理
@JsonIgnoreProperties(ignoreUnknown = true)  //过滤多余json字段
@Data
public class NvrDeviceInfoData {
    @JsonProperty(value = "deviceSerial")
    private String deviceSerial; // 设备序列号
    @JsonProperty(value = "status")
    private int status; // 在线状态：0-不在线，1-在线
    @JsonProperty(value = "channelInfoList")
    private List<NvrChannelStatus> channelInfoList;
}
