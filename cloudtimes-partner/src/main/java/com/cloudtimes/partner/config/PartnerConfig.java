package com.cloudtimes.partner.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class PartnerConfig {
    @Value("${hik.app_key}")
    private String hikAppKey;
    @Value("${hik.app_secret}")
    private String hikAppSecret;
    @Value("${wx.appid}")
    private String wxAppId;
    @Value("${wx.mch_id}")
    private String wxMchId;
    @Value("${wx.mch_key}")
    private String wxMchKey;
    @Value("${wx.secret}")
    private String wxAppSecret;
    @Value("${agora.appid}")
    private String agoraAppId;
    @Value("${agora.app_certificate}")
    private String agoraCertificate;
    @Value("${shouqianba.app_id}")
    private String shouqianbaAppId;
    @Value("${shouqianba.vendor_sn}")
    private String shouqianbaVendorSn;
    @Value("${shouqianba.vendor_key}")
    private String shouqianbaVendorKey;
    @Value("${shouqianba.cis_app_id}")
    private String shouqianbaCisAppId;
    @Value("${shouqianba.cis_client_id}")
    private String shouqianbaCisClientId;
    @Value("${shouqianba.shouqb_cis_pub_key}")
    private String shouqianbaShouqbCisPubKey;
    @Value("${shouqianba.shouqb_pub_key}")
    private String shouqianbaShouqbPubKey;
    @Value("${shouqianba.ct_priv_key}")
    private String shouqianbaCtPrivKey;
    @Value("${shouqianba.ct_pub_key}")
    private String shouqianbaCtPubKey;
    @Value("${wiegand.http_host}")
    private String wiegandHttpHost;
    @Value("${wiegand.ws_host}")
    private String wiegandWsHost;
}
