package com.cloudtimes.common.mq;

import lombok.Data;

@Data
public class SendOrderData {
    private String orderId;
    private String dynamicQrCode;
}