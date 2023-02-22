package com.cloudtimes.partner.pay.shouqianba.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class B2CProfitSharingData {
    @JsonProperty(value = "sharing_flag")
    private String sharingFlag;// 分账标识	string	Y	0: 不分账1：分账
    @JsonProperty(value = "sharing_type")
    private String sharingType;// 分账类型	string	Y	1: 按比例分 3: 按金额分
    @JsonProperty(value = "model_id")
    private String modelId;// 分账模型编号	string	Y	m1230000
    @JsonProperty(value = "sharing_notify_url")
    private String sharingNotifyUrl;// 分账回调地址	string(256)	N	https://yourdomain
    @JsonProperty(value = "receivers")
    private List<B2CProfitSharingReceiver> receivers;// 分账收款方	[]	Y
}
