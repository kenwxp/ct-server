package com.cloudtimes.common.constant;

/**
 * 用户常量信息
 *
 * @author tank
 */
public class RocketMQConstants {
    /**
     * 硬件设备消息F主题
     */
    public static final String TOPIC_DEVICE = "TOPIC_DEVICE";

    public static final String WS_CASH_DEVICE = "WS_CASH_DEVICE";
    public static final String WS_WEB_MESSAGE = "WS_WEB_MESSAGE";

    //门禁报文处理主体
    public static final String CT_DOOR_MESSAGE = "CT_DOOR_MESSAGE";
    //门禁操作相关模块
    public static final String CT_OPEN_DOOR = "CT_OPEN_DOOR";
    //订单相关模块监听主题
    public static final String CT_PAY_ORDER = "CT_PAY_ORDER";

    //摄像机状态监控主题
    public static final String CT_MONITOR_CAMARE_DEVICE = "CT_MONITOR_CAMARE_DEVICE";
}
