package com.cloudtimes.common.mq;

import lombok.Data;

@Data
public class CashMqData {
    private String deviceId;
    private String option;
    private Object data;
}
