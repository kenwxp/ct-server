package com.cloudtimes.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
}
