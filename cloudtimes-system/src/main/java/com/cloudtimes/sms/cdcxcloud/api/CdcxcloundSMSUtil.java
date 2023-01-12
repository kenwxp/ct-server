package com.cloudtimes.sms.cdcxcloud.api;

import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.sms.cdcxcloud.constant.ApiRequestMethodEnum;
import com.cloudtimes.sms.cdcxcloud.constant.SmsActionEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 创信 短信渠道商
 */
public class CdcxcloundSMSUtil {

    public static void semdSms(String body, String phone) {
        Map<String, Object> p = new HashMap<String, Object>();
        p.put("account", "922425");
        p.put("password", "WERA24");
        p.put("mobile", phone);
        p.put("content", body);
        p.put("extno", "10690446");
        //接入码
        SmsHttpApi smsHttpApi = new SmsHttpApi(ApiRequestMethodEnum.get);
        try {
            //发短信
            String result = "";
            System.out.println("发送短信,接收手机：" + phone + "内容:[" + body + "]");
            result = smsHttpApi.doAction(p, SmsActionEnum.send);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("发送短信失败！");
        }
    }

}
