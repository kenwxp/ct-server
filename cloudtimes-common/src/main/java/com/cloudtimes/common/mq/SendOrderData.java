package com.cloudtimes.common.mq;

public class SendOrderData {
    private String orderId;
    private String dynamicQrCode;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDynamicQrCode() {
        return dynamicQrCode;
    }

    public void setDynamicQrCode(String dynamicQrCode) {
        this.dynamicQrCode = dynamicQrCode;
    }

}