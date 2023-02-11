package com.cloudtimes.partner.weixin.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@JacksonXmlRootElement(localName = "xml")
@Data
public class WxpayfaceAuthInfoResp {
    @JacksonXmlProperty(localName = "returnCode")
    private String return_code;// 是	string(16)	错误码。公共定义见 公共错误码
    @JacksonXmlProperty(localName = "returnMsg")
    private String return_msg;// 是	string(128)	对错误码的描述
    @JacksonXmlProperty(localName = "authinfo")
    private String authinfo;// 是	string(4096)	SDK调用凭证。用于调用SDK的人脸识别接口。
    @JacksonXmlProperty(localName = "expiresIn")
    private String expires_in;// 否	int	authinfo的有效时间, 单位秒。 例如: 3600
    @JacksonXmlProperty(localName = "nonceStr")
    private String nonce_str;// 是	string(32)	随机字符串
    @JacksonXmlProperty(localName = "sign")
    private String sign;// 是	string(32)	响应结果签名
    @JacksonXmlProperty(localName = "appid")
    private String appid;// 是	string(32)	公众号
    @JacksonXmlProperty(localName = "mchId")
    private String mch_id;// 否	string(32)	商户号
    @JacksonXmlProperty(localName = "subAppid")
    private String sub_appid;// 否	string(32)	子商户公众账号ID(服务商模式)
    @JacksonXmlProperty(localName = "subMchId")
    private String sub_mch_id;// 是	string(32)	子商户号(服务商模式)
}
