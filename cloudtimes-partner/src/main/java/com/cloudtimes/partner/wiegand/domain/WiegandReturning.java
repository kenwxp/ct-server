package com.cloudtimes.partner.wiegand.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WiegandReturning {
    private boolean success;
    private int code;
    private String msg;

    public WiegandReturning(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static WiegandReturning success() {
        return new WiegandReturning(0, "操作成功");
    }

    public static WiegandReturning error() {
        return WiegandReturning.error("操作失败");
    }

    public static WiegandReturning error(String msg) {
        return new WiegandReturning(1, msg);
    }

}
