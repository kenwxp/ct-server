package com.cloudtimes.partner.pay.shouqianba.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BuzResponse {
    @JsonProperty(value = "result_code")
    private String resultCode;
    @JsonProperty(value = "error_code")
    private String errorCode;
    @JsonProperty(value = "error_code_standard")
    private String errorCodeStandard;
    @JsonProperty(value = "error_message")
    private String errorMessage;
    @JsonProperty(value = "data")
    private Object data;
}