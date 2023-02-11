package com.cloudtimes.partner.weixin.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@JacksonXmlRootElement(localName = "xml")
@Data
public class WxpayfaceAuthInfoReq {
    @JacksonXmlProperty(localName = "store_id")
    private String storeId;    // 是    门店编号， 由商户定义， 各门店唯一。
    @JacksonXmlProperty(localName = "store_name")
    private String storeName;    // 是门店名称，由商户定义。（可用于展示）
    @JacksonXmlProperty(localName = "device_id")
    private String deviceId;    // 是    终端设备编号，由商户定义。
//    @JacksonXmlProperty(localName = "attach")
//    private String attach;    // 否    附加字段。字段格式使用Json
    @JacksonXmlProperty(localName = "rawdata")
    private String rawdata;    // 是	初始化数据。由微信人脸SDK的接口返回。
    @JacksonXmlProperty(localName = "appid")
    private String appid;    // 是    商户号绑定的公众号/小程序 appid
    @JacksonXmlProperty(localName = "mch_id")
    private String mchId;    // 是    商户号
//    @JacksonXmlProperty(localName = "sub_appid")
//    private String subAppid;    // 否    子商户绑定的公众号/小程序 appid(服务商模式)
//    @JacksonXmlProperty(localName = "sub_mch_id")
//    private String subMchId;    // 否    子商户号(服务商模式)
    @JacksonXmlProperty(localName = "now")
    private int now;    // 是取当前时间，10位unix时间戳。 例如：1239878956
    @JacksonXmlProperty(localName = "version")
    private String version;    // 是    版本号。固定为1
    @JacksonXmlProperty(localName = "sign_type")
    private String signType;    // 是    签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
    @JacksonXmlProperty(localName = "nonce_str")
    private String nonceStr;    // 是    随机字符串，不长于32位
    @JacksonXmlProperty(localName = "sign")
    private String sign;    // 是    参数签名。详见微信支付签名算法
}
