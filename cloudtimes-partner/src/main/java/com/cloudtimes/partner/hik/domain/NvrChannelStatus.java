package com.cloudtimes.partner.hik.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL) //null的字段不处理
@JsonIgnoreProperties(ignoreUnknown = true)  //过滤多余json字段
@Data
public class NvrChannelStatus {
    @JsonProperty(value = "superDevChannel")
    private int superDevChannel; //	通道号
    @JsonProperty(value = "status")
    private int status;        // 0-离线 1-在线 2-未上报,在托管的情况下代表无权限
}
