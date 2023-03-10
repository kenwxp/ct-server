package com.cloudtimes.partner.hik.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL) //null的字段不处理
@JsonIgnoreProperties(ignoreUnknown = true)  //过滤多余json字段
@Data
public class AccessTokenData {
    @JsonProperty(value = "accessToken")
    private String accessToken; // 设备序列号
    @JsonProperty(value = "expireTime")
    private long expireTime; // 设备名称
}
