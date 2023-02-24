package com.cloudtimes.common.enums;

public enum PayOrderOption {
    QUERY_PAY_ORDER(0),

    CANCEL_PAY_ORDER(1),

    MAINTAIN_STOCK(2);

    private int code;

    PayOrderOption(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
