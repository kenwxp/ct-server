package com.cloudtimes.common.sms.cdcxcloud.api;


import com.cloudtimes.common.sms.cdcxcloud.constant.ApiRequestMethodEnum;
import com.cloudtimes.common.sms.cdcxcloud.constant.SmsActionEnum;
import com.cloudtimes.common.sms.cdcxcloud.net.HttpRequestor;

import java.util.Map;

/**
 * Created by mac on 16/6/17.
 */
public class SmsHttpApi {
    private static final String host = "http://api.cdcxcloud.com:7862/sms?action=%s";

    private ApiRequestMethodEnum method;

    public SmsHttpApi(ApiRequestMethodEnum methodEnum) {
        method = methodEnum;
    }

    private static String getUrl(SmsActionEnum action) {
        return String.format(host, action.getDes());
    }

    private static String getUrl(SmsActionEnum action, String params) {
        StringBuffer urlBuffer = new StringBuffer(getUrl(action));
        urlBuffer.append("&").append(params);
        return urlBuffer.toString();
    }

    private HttpRequestor httpRequestor() {
        return new HttpRequestor();
    }

    public String doAction(Map<String, Object> params, SmsActionEnum smsActionEnum) throws Exception {
        HttpRequestor requestor = httpRequestor();

        if (method == ApiRequestMethodEnum.get)
            return requestor.doGet(getUrl(smsActionEnum, requestor.parseParamsForMap(params)));
        else
            return requestor.doPost(getUrl(smsActionEnum), params);
    }

}
