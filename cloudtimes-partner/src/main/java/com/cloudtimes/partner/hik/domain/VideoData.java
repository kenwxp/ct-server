package com.cloudtimes.partner.hik.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)  //过滤多余json字段
@Data
public class VideoData {
    @JsonProperty(value = "id")
    private String id; // 状态描述
    @JsonProperty(value = "url")
    private String url; // 直播地址
    @JsonProperty(value = "expireTime")
    private String expireTime; // 直播地址有效期。expireTime参数为空时该字段无效 yyyy-mm-dd hh:mm:ss
    @JsonProperty(value = "token")
    private String token; // token
}
