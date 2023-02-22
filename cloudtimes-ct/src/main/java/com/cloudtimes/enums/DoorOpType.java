package com.cloudtimes.enums;

public enum DoorOpType {
    TRANS_OPEN_DOOR("0"), //交易开门

    EMERGENCY_OPEN_DOOR("1"), //应急开门

    SETTING_DOOR_ACCESS("2"), //设置密码

    TRIGGER_OPEN_DOOR("3"), //触发开门

    OWNER_OPEN_DOOR("4"), //店家开门

    FORCE_LOCK_DOOR("5"), //强锁

    UNLOCK_DOOR("6");//解锁

    private String code;

    DoorOpType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
