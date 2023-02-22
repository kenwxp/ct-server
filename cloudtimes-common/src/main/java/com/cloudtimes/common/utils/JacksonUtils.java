package com.cloudtimes.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JacksonUtils {
    private static Logger log = LoggerFactory.getLogger(JacksonUtils.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static ObjectMapper getInstance() {
        return objectMapper;
    }

    public static String toString(Object o) {
        String s = "";
        try {
            s = objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("转json字符串失败：", e);
        }
        return s;
    }

    public static <T> T parseObject(String rawString, Class<T> tClass) {
        try {
            return objectMapper.readValue(rawString, tClass);
        } catch (JsonProcessingException e) {
            log.error("解析json对象失败：", e);
        }
        return null;
    }

    public static <T> T convertObject(Object o, Class<T> tClass) {
        try {
            return objectMapper.convertValue(o, tClass);
        } catch (IllegalArgumentException e) {
            log.error("转换json对象失败：", e);
        }
        return null;
    }
}
