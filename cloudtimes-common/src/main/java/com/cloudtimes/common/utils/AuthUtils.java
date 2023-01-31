package com.cloudtimes.common.utils;

import org.apache.poi.ss.formula.functions.T;

public class AuthUtils {

    private static ThreadLocal<Object> authObject = new ThreadLocal<>();

    public static void setObject(Object object) {
        authObject.set(object);
    }

    public static <T> T getObject() {
        return (T) authObject.get();
    }

}
