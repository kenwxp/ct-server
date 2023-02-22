package com.cloudtimes.common;

import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.common.utils.NumberUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * 业务编号生产工具类
 *
 * @author wangxp
 */
public class NoUtils {

    public static String genTaskSerial() {
        return "TK" + DateUtils.dateTimeNow(DateUtils.YYYYMMDDHHMMSS) + NumberUtils.genRandNum(6);
    }

    public static String genEventSerial() {
        return "EV" + DateUtils.dateTimeNow(DateUtils.YYYYMMDDHHMMSS) + NumberUtils.genRandNum(6);
    }

    public static String genTransSerial() {
        return "TR" + DateUtils.dateTimeNow(DateUtils.YYYYMMDDHHMMSS) + NumberUtils.genRandNum(6);
    }

    public static String genNormalTransSerial() {
        return "NR" + DateUtils.dateTimeNow(DateUtils.YYYYMMDDHHMMSS) + NumberUtils.genRandNum(6);
    }

    public static String genBillSerial() {
        return "BL" + DateUtils.dateTimeNow(DateUtils.YYYYMMDDHHMMSS) + NumberUtils.genRandNum(6);
    }

    public static String genNormalBillSerial() {
        return "NB" + DateUtils.dateTimeNow(DateUtils.YYYYMMDDHHMMSS) + NumberUtils.genRandNum(6);
    }

    public static String genMissionSerial() {
        return "MS" + DateUtils.dateTimeNow(DateUtils.YYYYMMDDHHMMSS) + NumberUtils.genRandNum(6);
    }

    public static String genMerchantsNo() {
        return "ZM" + StringUtils.upperCase(NumberUtils.getRandomENString(4)) + NumberUtils.genRandNum(8);
    }

    public static String genShopNo() {
        return "ZS" + StringUtils.upperCase(NumberUtils.getRandomENString(4)) + NumberUtils.genRandNum(8);
    }

    public static String genDeviceCode() {
        return "ZD" + StringUtils.upperCase(NumberUtils.getRandomENString(4)) + NumberUtils.genRandNum(8);
    }

    public static String genPayOrderNo(String orderId) {
        return orderId + "-" + NumberUtils.genRandNum(6);
    }

    public static String parseOrderNo(String rawOrderNo) {
        String[] arr = rawOrderNo.split("-");
        if (arr.length > 0) {
            return arr[0];
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(new Date().getYear());
        System.out.println(new Date().getMonth());
        System.out.println((new Date().getYear() + 1900) * 100 + new Date().getMonth());
    }
}
