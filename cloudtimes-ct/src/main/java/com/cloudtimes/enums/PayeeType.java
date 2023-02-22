package com.cloudtimes.enums;

/**
 * 收款主体类型 1-收银机 2-门店 3-店老板 4-会员
 */
public enum PayeeType {
    CASH("1"),
    STORE("2"),
    BOSS("3"),
    MEMBER("4");
    private String code;

    PayeeType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
