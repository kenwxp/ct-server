package com.cloudtimes.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlUtils {
    static Logger log = LoggerFactory.getLogger(XmlUtils.class);

    public static String formatXml(Object obj) {
        ObjectMapper mapper = new XmlMapper();
        String s = "";
        try {
            s = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("格式化xml错误:", e);
        }
        return s;
    }

    public static <T> T parseXml(String xml, Class<T> valueType) {
        ObjectMapper mapper = new XmlMapper();
        T data = null;
        try {
            data = mapper.readValue(xml, valueType);
        } catch (JsonProcessingException e) {
            log.error("解析xml错误:", e);
        }
        return data;
    }

}
