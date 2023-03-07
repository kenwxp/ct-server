package com.cloudtimes.serving.mobile.domain;


/**
 * 门店查询类型（0-全部门店，1-具体门店）
 */
public enum SearchType {

    ALL("0"),
    ONE("1");
    private String code;
    SearchType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
