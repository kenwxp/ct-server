package com.cloudtimes.partner.pay.shouqianba.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL) //null的字段不处理
@JsonIgnoreProperties(ignoreUnknown = true)  //过滤多余json字段
@Data
public class B2CProfitSharingReceiver {
    @JsonProperty(value = "id")
    private String id;// 收款方编号		N	id和client_sn二选一，不能同时为空
    @JsonProperty(value = "client_sn")
    private String clientSn;// 自定义收款方编号		N	id和client_sn二选一，不能同时为空
    @JsonProperty(value = "ratio")
    private String ratio;// 分账比例%。最小 0.001%，最大100%	string	条件必填	sharing_type为1时必填。例：0.003%
    @JsonProperty(value = "sharing_amount")
    private String sharingAmount;// 分账金额，单位为分	string	条件必填	sharing_type为3时必填。例：20（即0.2元）
}
