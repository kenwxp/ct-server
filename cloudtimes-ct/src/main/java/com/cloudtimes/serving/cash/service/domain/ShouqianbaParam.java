package com.cloudtimes.serving.cash.service.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 收银机特有信息对象 ct_device_cash
 *
 * @author tank
 * @date 2023-01-17
 */

@JsonInclude(JsonInclude.Include.NON_NULL) //null的字段不处理
@JsonIgnoreProperties(ignoreUnknown = true)  //过滤多余json字段
@Data
public class ShouqianbaParam {
    /**
     * 终端ID
     */
    @JsonProperty(value = "device_no")
    private String deviceNo;

    /**
     * 终端SN
     */
    @JsonProperty(value = "terminal_sn")
    private String terminalSn;

    /**
     * 终端密钥
     */
    @JsonProperty(value = "terminal_key")
    private String terminalKey;

}
