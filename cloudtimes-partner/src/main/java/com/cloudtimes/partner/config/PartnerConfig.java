package com.cloudtimes.partner.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PartnerConfig {
    @Value("${hik.app_key}")
    private String hikAppKey;
    @Value("${hik.app_secret}")
    private String hikAppSecret;
    @Value("${wx.appid}")
    private String wxAppId;
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


    public String getHikAppKey() {
        return hikAppKey;
    }

    public void setHikAppKey(String hikAppKey) {
        this.hikAppKey = hikAppKey;
    }

    public String getHikAppSecret() {
        return hikAppSecret;
    }

    public void setHikAppSecret(String hikAppSecret) {
        this.hikAppSecret = hikAppSecret;
    }

    public String getWxAppId() {
        return wxAppId;
    }

    public void setWxAppId(String wxAppId) {
        this.wxAppId = wxAppId;
    }

    public String getWxAppSecret() {
        return wxAppSecret;
    }

    public void setWxAppSecret(String wxAppSecret) {
        this.wxAppSecret = wxAppSecret;
    }

    public String getAgoraAppId() {
        return agoraAppId;
    }

    public void setAgoraAppId(String agoraAppId) {
        this.agoraAppId = agoraAppId;
    }

    public String getAgoraCertificate() {
        return agoraCertificate;
    }

    public void setAgoraCertificate(String agoraCertificate) {
        this.agoraCertificate = agoraCertificate;
    }

    public String getShouqianbaAppId() {
        return shouqianbaAppId;
    }

    public void setShouqianbaAppId(String shouqianbaAppId) {
        this.shouqianbaAppId = shouqianbaAppId;
    }

    public String getShouqianbaVendorSn() {
        return shouqianbaVendorSn;
    }

    public void setShouqianbaVendorSn(String shouqianbaVendorSn) {
        this.shouqianbaVendorSn = shouqianbaVendorSn;
    }

    public String getShouqianbaVendorKey() {
        return shouqianbaVendorKey;
    }

    public void setShouqianbaVendorKey(String shouqianbaVendorKey) {
        this.shouqianbaVendorKey = shouqianbaVendorKey;
    }

    public String getShouqianbaCisAppId() {
        return shouqianbaCisAppId;
    }

    public void setShouqianbaCisAppId(String shouqianbaCisAppId) {
        this.shouqianbaCisAppId = shouqianbaCisAppId;
    }

    public String getShouqianbaCisClientId() {
        return shouqianbaCisClientId;
    }

    public void setShouqianbaCisClientId(String shouqianbaCisClientId) {
        this.shouqianbaCisClientId = shouqianbaCisClientId;
    }

    public String getShouqianbaShouqbCisPubKey() {
        return shouqianbaShouqbCisPubKey;
    }

    public void setShouqianbaShouqbCisPubKey(String shouqianbaShouqbCisPubKey) {
        this.shouqianbaShouqbCisPubKey = shouqianbaShouqbCisPubKey;
    }

    public String getShouqianbaShouqbPubKey() {
        return shouqianbaShouqbPubKey;
    }

    public void setShouqianbaShouqbPubKey(String shouqianbaShouqbPubKey) {
        this.shouqianbaShouqbPubKey = shouqianbaShouqbPubKey;
    }

    public String getShouqianbaCtPrivKey() {
        return shouqianbaCtPrivKey;
    }

    public void setShouqianbaCtPrivKey(String shouqianbaCtPrivKey) {
        this.shouqianbaCtPrivKey = shouqianbaCtPrivKey;
    }

    public String getShouqianbaCtPubKey() {
        return shouqianbaCtPubKey;
    }

    public void setShouqianbaCtPubKey(String shouqianbaCtPubKey) {
        this.shouqianbaCtPubKey = shouqianbaCtPubKey;
    }
    public String getWiegandHttpHost() {
        return wiegandHttpHost;
    }

    public void setWiegandHttpHost(String wiegandHttpHost) {
        this.wiegandHttpHost = wiegandHttpHost;
    }

    public String getWiegandWsHost() {
        return wiegandWsHost;
    }

    public void setWiegandWsHost(String wiegandWsHost) {
        this.wiegandWsHost = wiegandWsHost;
    }


}
