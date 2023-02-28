package com.cloudtimes.partner.hik.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)  //过滤多余json字段
@Data
public class CommonResp<T> {
    @JsonProperty(value = "code")
    private String code;
    @JsonProperty(value = "msg")
    private String msg;
    @JsonProperty(value = "msg")
    private T data;
}
