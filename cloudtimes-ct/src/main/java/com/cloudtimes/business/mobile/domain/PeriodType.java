package com.cloudtimes.business.mobile.domain;


/**
 * 时间段类型（0-自定义 1-今天，2-昨天，3-本周，4-本月）
 */
public enum PeriodType {

    SELF_DEFINE("0"),
    TODAY("1"),
    YESTERDAY("2"),
    THIS_WEEK("3"),
    THIS_MONTH("4");
    private String code;

    PeriodType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
