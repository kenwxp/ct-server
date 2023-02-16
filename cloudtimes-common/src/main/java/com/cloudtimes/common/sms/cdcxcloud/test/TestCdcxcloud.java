package com.cloudtimes.common.sms.cdcxcloud.test;


import com.cloudtimes.common.sms.cdcxcloud.api.SmsHttpApi;
import com.cloudtimes.common.sms.cdcxcloud.constant.ApiRequestMethodEnum;
import com.cloudtimes.common.sms.cdcxcloud.constant.SmsActionEnum;

import java.util.HashMap;
import java.util.Map;

public class TestCdcxcloud {


    public static void main(String[] args) {
        Map<String, Object> p = new HashMap<String, Object>();
        p.put("account", "922459");
        p.put("password", "8eYtxi");
        p.put("mobile", "18316699739");
        p.put("content", "【云时代】你的验证码是2386,3分钟内有效！");
        p.put("extno", "10690468");
        //接入码
        SmsHttpApi smsHttpApi = new SmsHttpApi(ApiRequestMethodEnum.get);
        try {
            //发短信
            String result = "";
            System.out.println("发送短信-------------------------------------------");
            result = smsHttpApi.doAction(p, SmsActionEnum.send);
            System.out.println(result);
//
//            //查询余额
//            System.out.println("查询余额-------------------------------------------");
//            result = smsHttpApi.doAction(p, SmsActionEnum.overage);
//            System.out.println(result);
//
//            //上行接口(回复)
//            System.out.println("上行接口(回复)-------------------------------------------");
//            result = smsHttpApi.doAction(p, SmsActionEnum.mo);
//            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
