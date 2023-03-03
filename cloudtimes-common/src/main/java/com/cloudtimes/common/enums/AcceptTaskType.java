package com.cloudtimes.common.enums;

/**
 * 接单状态
 */
public enum AcceptTaskType {
    /**
     * 开始接单
     */
    START("0"),
    /**
     * 暂停接单
     */
    PAUSE("1"),
    /**
     * 结束接单
     */
    COMPLETE("2");
    private String code;

    AcceptTaskType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
