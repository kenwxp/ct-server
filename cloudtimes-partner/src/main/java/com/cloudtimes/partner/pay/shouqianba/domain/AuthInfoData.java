package com.cloudtimes.partner.pay.shouqianba.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL) //null的字段不处理
@JsonIgnoreProperties(ignoreUnknown = true)  //过滤多余json字段
@Data
@ApiModel(value = "AuthInfoData", description = "凭证数据")
public class AuthInfoData {
    @ApiModelProperty(value = "公众号appid", required = true)
    @JsonProperty(value = "appid")
    private String appid;// 是	string(32)	公众号

    @ApiModelProperty(value = "子商户公众账号ID(服务商模式)", required = true)
    @JsonProperty(value = "sub_appid")
    private String subAppid;// 否	string(32)	子商户公众账号ID(服务商模式)

    @ApiModelProperty(value = "商户号", required = true)
    @JsonProperty(value = "mch_id")
    private String mchId;// 否	string(32)	商户号

    @ApiModelProperty(value = "子商户号(服务商模式)", required = true)
    @JsonProperty(value = "sub_mch_id")
    private String subMchId;// 是	string(32)	子商户号(服务商模式)

    @ApiModelProperty(value = "authinfo的有效时间, 单位秒。 例如: 3600", required = true)
    @JsonProperty(value = "expires_in")
    private String expiresIn;// 否	int	authinfo的有效时间, 单位秒。 例如: 3600

    @ApiModelProperty(value = "SDK调用凭证。用于调用SDK的人脸识别接口。", required = true)
    @JsonProperty(value = "authinfo")
    private String authinfo;// 是	string(4096)	SDK调用凭证。用于调用SDK的人脸识别接口。
}
