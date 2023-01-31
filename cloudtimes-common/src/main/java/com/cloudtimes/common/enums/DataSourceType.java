package com.cloudtimes.common.enums;

/**
 * 数据源
 *
 * @author tank
 */
public enum DataSourceType {
    /**
     * 主库
     */
    MASTER,

    /**
     * 从库
     */
    SLAVE,
    /**
     * 系统
     */
    SYS,
    /**
     * 定时任务
     */
    QUARTZ,
    /**
     * 业务
     */
    CT,

    /**
     * 分库分表
     */
    SHARDING

}
