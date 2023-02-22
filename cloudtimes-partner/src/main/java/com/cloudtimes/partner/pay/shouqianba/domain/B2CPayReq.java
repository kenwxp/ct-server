package com.cloudtimes.partner.pay.shouqianba.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL) //null的字段不处理
@JsonIgnoreProperties(ignoreUnknown = true)  //过滤多余json字段
@Data
public class B2CPayReq {
    @JsonProperty(value = "terminal_sn")
    private String terminalSN; //收钱吧终端ID	收钱吧终端ID，不超过32位的纯数字
    @JsonProperty(value = "client_sn")
    private String clientSN; //商户系统订单号	必须在商户系统内唯一；且长度不超过32字节
    @JsonProperty(value = "total_amount")
    private String totalAmount; //交易总金额	以分为单位,不超过10位纯数字字符串,超过1亿元的收款请使用银行转账
    @JsonProperty(value = "dynamic_id")
    private String dynamicId; //条码内容	不超过32字节
    @JsonProperty(value = "subject")
    private String subject; //交易简介	本次交易的简要介绍
    @JsonProperty(value = "operator")
    private String operator; //门店操作员	发起本次交易的操作员
    @JsonProperty(value = "notify_url")
    private String notifyUrl; //回调	支付回调的地址
    @JsonProperty(value = "reflect")
    private String reflect; //反射参数 透传订单号
    @JsonProperty(value = "profit_sharing")
    private B2CProfitSharingData profitSharing; //反射参数 透传订单号
}
