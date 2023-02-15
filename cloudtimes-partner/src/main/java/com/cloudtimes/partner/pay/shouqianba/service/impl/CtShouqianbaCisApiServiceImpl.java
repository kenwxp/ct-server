package com.cloudtimes.partner.pay.shouqianba.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.cloudtimes.common.utils.http.HttpUtils;
import com.cloudtimes.common.utils.sign.Base64;
import com.cloudtimes.common.utils.sign.RSAUtil;
import com.cloudtimes.partner.config.PartnerConfig;
import com.cloudtimes.partner.pay.shouqianba.domain.ShouqianbaConstant;
import com.cloudtimes.partner.pay.shouqianba.service.ICtShouqianbaCisApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CtShouqianbaCisApiServiceImpl implements ICtShouqianbaCisApiService {
    @Autowired
    private PartnerConfig config;

    /**
     * 查询商户申请记录
     *
     * @param merchantsNo 商户号 本系统生产，用于关联收钱吧商户
     * @return map
     * "client_id"           String //	客户编号	Y	客户编号
     * "client_merchant_sn"  String //	客户系统商户号	Y	客户系统商户号
     * "merchant_sn"         String //	收钱吧商户号	N	入网成功后返回该字段
     * "name"                String //	商户名称	Y	提交申请时传入的商户名称
     * "status"              int //	申请状态	Y	1:入网中、2:待账户验证、3:待签约、4:入网成功、5：开通中、6：待店铺认证、9:已驳回
     * "sign_url"            String //	签约链接	N	1、请联系人用微信扫码打开签约链接，根据页面指引完成绑定微信号、账户验证、签约等操作2、绑定微信号后，后续申请单进度可通过公众号自动通知联系人
     * "reject_reason"       String //	驳回原因	N	驳回说明
     * "merchant_qrcode"     String //	商户二维码	N	申请状态为待店铺认证时返回，需商户实地拍摄门店照片
     * "audit_detail"    List   //	审核详情	N	各资料驳回原因，当申请状态为已驳回时，才返回该字段
     * * "field"         // 驳回信息属性名
     * * "field_name"    // 驳回信息名称
     * * "reject_reason" // 驳回原因
     */
    @Override
    public Map<String, Object> queryMerchantsApply(String merchantsNo) {
        JSONObject reqBody = new JSONObject();
        reqBody.put("client_merchant_sn", merchantsNo);
        reqBody.put("client_id", config.getShouqianbaCisClientId());
        reqBody.put("vendor_sn", config.getShouqianbaVendorSn());
        return sendCisHttp(ShouqianbaConstant.queryMerchantsApply, reqBody);
    }

    private Map<String, Object> sendCisHttp(String path, JSONObject reqBody) {
        JSONObject reqObj = new JSONObject();
        JSONObject requestObj = new JSONObject();
        // 封装并设置header
        JSONObject reqHeader = new JSONObject();
        reqHeader.put("version", "1.0.0");
        reqHeader.put("appid", config.getShouqianbaCisAppId());
        reqHeader.put("sign_type", "SHA1");
        reqHeader.put("request_time", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(new Date()));
        reqHeader.put("reserve", "{}");
        requestObj.put("head", reqHeader);
        // 设置body
        requestObj.put("body", reqBody);
        reqObj.put("request", requestObj);
        // 签名
        byte[] signRaw = RSAUtil.sign(
                RSAUtil.parsePKCS1KeyStr(config.getShouqianbaCtPrivKey()),
                requestObj.toString());
        String signature = Base64.encode(signRaw);
        reqObj.put("signature", signature);
        HashMap<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json; charset=utf-8");
        try {
            String responseStr = HttpUtils.sendJsonPost(ShouqianbaConstant.cisApiHost + path, reqObj.toString(), header);
            Map<String, Object> responseObj = JSON.parseObject(responseStr, Map.class);
            String retSign = (String) responseObj.get("signature");
            // 验签
            if (RSAUtil.verify(RSAUtil.parsePublicKeyStr(config.getShouqianbaShouqbCisPubKey()), getResponseStr(responseStr), retSign)) {
                return responseObj;
            } else {
                throw new RuntimeException(path + " 验签失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getResponseStr(String raw) {
        int beginIndex = raw.indexOf("response");
        int endIndex = raw.indexOf("signature");
        return raw.substring(beginIndex + 10, endIndex - 2);
    }
}
