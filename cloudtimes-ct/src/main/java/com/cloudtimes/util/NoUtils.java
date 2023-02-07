package com.cloudtimes.util;

import com.cloudtimes.common.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 业务编号生产工具类
 *
 * @author wangxp
 */
public class NoUtils {

    public static String genTaskSerial() {
        return "TK" + DateUtils.dateTimeNow(DateUtils.YYYYMMDDHHMMSS) + genRandNum(6);
    }

    public static String genEventSerial() {
        return "EV" + DateUtils.dateTimeNow(DateUtils.YYYYMMDDHHMMSS) + genRandNum(6);
    }

    public static String genTransSerial() {
        return "TR" + DateUtils.dateTimeNow(DateUtils.YYYYMMDDHHMMSS) + genRandNum(6);
    }

    public static String genNormalTransSerial() {
        return "NR" + DateUtils.dateTimeNow(DateUtils.YYYYMMDDHHMMSS) + genRandNum(6);
    }

    public static String genBillSerial() {
        return "BL" + DateUtils.dateTimeNow(DateUtils.YYYYMMDDHHMMSS) + genRandNum(6);
    }

    public static String genNormalBillSerial() {
        return "NB" + DateUtils.dateTimeNow(DateUtils.YYYYMMDDHHMMSS) + genRandNum(6);
    }

    public static String genMissionSerial() {
        return "MS" + DateUtils.dateTimeNow(DateUtils.YYYYMMDDHHMMSS) + genRandNum(6);
    }

    public static String genMerchantsNo() {
        return "ZM" + StringUtils.upperCase(getRandomString(4)) + genRandNum(8);
    }

    public static String genShopNo() {
        return "ZS" + StringUtils.upperCase(getRandomString(4)) + genRandNum(8);
    }

    public static String genDeviceCode() {
        return "ZD" + StringUtils.upperCase(getRandomString(4)) + genRandNum(8);
    }

    public static String genPayBillNo(String billSerial) {
        return billSerial + "-" + genRandNum(6);
    }

    /**
     * 生产指定位数的随机数
     *
     * @param length 随机数位数
     */
    public static String genRandNum(int length) {
        String str = "0123456789";
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < length; i++) {
                int number = random.nextInt(10);
                sb.append(str.charAt(number));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * length用户要求产生字符串的长度
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < length; i++) {
                int number = random.nextInt(52);
                sb.append(str.charAt(number));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(genTaskSerial());
    }
}
