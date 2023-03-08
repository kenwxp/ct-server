package com.cloudtimes.partner.pay.shouqianba.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)  //过滤多余json字段
@Data
public class ActivateResponse {
    @JsonProperty(value = "terminal_sn")
    private String terminalSn;
    @JsonProperty(value = "terminal_key")
    private String terminalKey;
}
