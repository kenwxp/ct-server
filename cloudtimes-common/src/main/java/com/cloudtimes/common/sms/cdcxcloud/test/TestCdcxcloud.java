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
        p.put("mobile", "13544117721");
        p.put("content", "你好，顾客，昨日我们发现您有未支付的三件商品，请支付，监控收集了您的异常行为，请回复，门店24小时警方联网。");
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
