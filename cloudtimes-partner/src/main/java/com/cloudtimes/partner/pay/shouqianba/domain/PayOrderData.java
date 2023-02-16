package com.cloudtimes.partner.pay.shouqianba.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL) //null的字段不处理
@JsonIgnoreProperties(ignoreUnknown = true)  //过滤多余json字段
@Data
public class PayOrderData {
    @JsonProperty(value = "sn")
    private String sn;// Y 收钱吧系统内部唯一订单号         "7892259488292938"
    @JsonProperty(value = "client_sn")
    private String clientSn;// Y 商户系统订单号                  "7654321132"
    @JsonProperty(value = "client_tsn")
    private String clientTsn;// Y 商户系统订单号                  "7654321132"
    @JsonProperty(value = "trade_no")
    private String tradeNo;// N 支付通道交易凭证号，只有支付成功时才有值返回  "2013112011001004330000121536"
    @JsonProperty(value = "status")
    private String status;// Y 本次操作产生的流水的状态       "SUCCESS"
    @JsonProperty(value = "order_status")
    private String orderStatus;// Y 当前订单状态                 "PAID"
    @JsonProperty(value = "payway")
    private String payway;// Y 一级支付方式，取值见附录《支付方式列表》  "1"
    @JsonProperty(value = "payway_name")
    private String paywayName;// Y 支付方式名称
    @JsonProperty(value = "sub_payway")
    private String subPayway;// Y 二级支付方式，取值见附录《二级支付方式列表》  "1"
    @JsonProperty(value = "payer_uid")
    private String payerUid;// N 支付平台（微信，支付宝）上的付款人ID   "2801003920293239230239"
    @JsonProperty(value = "payer_login")
    private String payerLogin;// N 支付平台上(微信，支付宝)的付款人账号	支付宝    "134**3920"
    @JsonProperty(value = "total_amount")
    private String totalAmount;// Y 本次交易总金额       "10000"
    @JsonProperty(value = "net_amount")
    private String netAmount;// Y 如果没有退款，这个字段等于total_amount。否则等于total_amount减去退款金额    "0"
    @JsonProperty(value = "settlement_amount")
    private String settlementAmount;// Y 本次支付金额   "10000"
    @JsonProperty(value = "subject")
    private String subject;// Y 本次交易概述   "Pizza"
    @JsonProperty(value = "finish_time")
    private String finishTime;// N 时间戳，只有order_status为最终状态时才会返回   "1449646835244"
    @JsonProperty(value = "channel_finish_time")
    private String channelFinishTime;// N 时间戳，只有支付成功时才有值返回    "1449646835244"
    @JsonProperty(value = "operator")
    private String operator;// Y 门店操作员    "张三丰"
    @JsonProperty(value = "reflect")
    private String reflect;  // N 透传参数    {"tips": "200"}
}
