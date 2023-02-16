package com.cloudtimes.common.sms.cdcxcloud.constant;

/**
 * Created by mac on 16/6/17.
 */
public enum ApiRequestMethodEnum {
    get("get"),
    post( "post");

    private String method;

    private ApiRequestMethodEnum(String string)

    {
        method=string;
    }

    public String getMethod()
    {
        return method;
    }
}
