package com.cloudtimes.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DecimalFormat;

public class NumberUtils {

    /**
     * 只留两位小数
     *
     * @param decimal
     * @return
     */
    public static BigDecimal bigDecimalFormat(BigDecimal decimal) {
        DecimalFormat df = new DecimalFormat("#0.00");
        df.setRoundingMode(RoundingMode.DOWN);
        return BigDecimal.valueOf(Double.parseDouble(df.format(decimal.doubleValue())));
    }

    /**
     * 保留两位小数
     *
     * @return
     */
    public static String bigDecimaFormatString(BigDecimal decimal) {
        DecimalFormat df = new DecimalFormat("#0.00");
        df.setRoundingMode(RoundingMode.DOWN);
        return df.format(decimal);
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
}
