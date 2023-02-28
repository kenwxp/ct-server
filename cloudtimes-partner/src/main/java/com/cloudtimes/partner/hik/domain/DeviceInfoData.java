package com.cloudtimes.partner.hik.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL) //null的字段不处理
@JsonIgnoreProperties(ignoreUnknown = true)  //过滤多余json字段
@Data
public class DeviceInfoData {
    @JsonProperty(value = "deviceSerial")
    private String deviceSerial; // 设备序列号
    @JsonProperty(value = "deviceName")
    private String deviceName; // 设备名称
    @JsonProperty(value = "model")
    private String model; // 设备型号，如CS-C2S-21WPFR-WX
    @JsonProperty(value = "status")
    private int status; // 在线状态：0-不在线，1-在线
    @JsonProperty(value = "defence")
    private int defence; // 具有防护能力的设备布撤防状态：0-睡眠，8-在家，16-外出，普通IPC布撤防状态：0-撤防，1-布防
    @JsonProperty(value = "isEncrypt")
    private int isEncrypt; // 是否加密：0-不加密，1-加密
    @JsonProperty(value = "alarmSoundMode")
    private int alarmSoundMode; // 告警声音模式：0-短叫，1-长叫，2-静音
    @JsonProperty(value = "offlineNotify")
    private int offlineNotify; // 设备下线是否通知：0-不通知 1-通知
    @JsonProperty(value = "category")
    private String category; // 设备大类
    @JsonProperty(value = "netType")
    private String netType; // 网络类型，如有线连接wire
    @JsonProperty(value = "signal")
    private String signal; // 信号强度(%)
}
