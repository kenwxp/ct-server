package com.cloudtimes.common.constant;

/**
 * 用户常量信息
 *
 * @author tank
 */
public interface RocketMQConstants {
    /**
     * 硬件设备消息F主题
     */
    public static final String TOPIC_DEVICE = "TOPIC_DEVICE";

    public static final String WS_CASH_DEVICE = "WS_CASH_DEVICE";

    /**
     * 指令选项
     */
    public static final String RECV_HEART_REPORT = "HEART-REPORT";// 心跳状态
    public static final String RECV_CALL_REQUIRE = "CALL-REQ"; // 呼叫客服
    public static final String SEND_DUTY_STATUS = "DUTY-STATUS";// 收银机值守状态
    public static final String SEND_CALL_DO = "CALL-DO"; // 加入频道
    public static final String SEND_BILL_SERIAL = "BILL-SERIAL"; // 推送单号
    public static final String SEND_PRODUCT_LIST = "PRODUCT-LIST";// 商品列表
}
