package com.cloudtimes.common.mq;

import com.cloudtimes.common.utils.DateUtils;
import lombok.Data;

import java.util.Date;

@Data
public class CashMqData {
    private String deviceId;
    private String option;
    private Object data;
    private Date sendTime;

    public CashMqData() {
        this.sendTime = DateUtils.getNowDate();
    }

    public CashMqData(String deviceId, String option) {
        this.deviceId = deviceId;
        this.option = option;
        this.sendTime = DateUtils.getNowDate();
    }

    public CashMqData(String deviceId, String option, Object data) {
        this.deviceId = deviceId;
        this.option = option;
        this.data = data;
        this.sendTime = DateUtils.getNowDate();
    }
}
