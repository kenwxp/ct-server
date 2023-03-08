package com.cloudtimes.partner.wiegand.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)  //过滤多余json字段
@Data
public class CommonResp {
    private String jsonrpc;
    private int id;
    private WiegandReturning result;
}
