package com.cloudtimes.supervise.table

import java.sql.JDBCType
import java.util.Date
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column


class CtEventsTable : AliasableSqlTable<CtEventsTable>("ct_events", ::CtEventsTable) {
    /** 编号 */
    val id = column<String>(name = "id", jdbcType = JDBCType.OTHER)

    /** 事件类型 */
    val eventType = column<String>(name = "event_type", jdbcType = JDBCType.CHAR)

    /** 事件子类型 */
    val subType = column<String>(name = "sub_type", jdbcType = JDBCType.CHAR)

    /** 事件名称 */
    val eventName = column<String>(name = "event_name", jdbcType = JDBCType.VARCHAR)

    /** 事件详情 */
    val content = column<String>(name = "content", jdbcType = JDBCType.VARCHAR)

    /** 发送者用户编码 */
    val sender = column<String>(name = "sender", jdbcType = JDBCType.OTHER)

    /** 接收者昵称/名称 */
    val senderName = column<String>(name = "sender_name", jdbcType = JDBCType.VARCHAR)

    /** 接收者用户编码 */
    val receiver = column<String>(name = "receiver", jdbcType = JDBCType.OTHER)

    /** 接收者昵称/名称 */
    val receiverName = column<String>(name = "receiver_name", jdbcType = JDBCType.VARCHAR)

    /** 任务编号 */
    val taskId = column<String>(name = "task_id", jdbcType = JDBCType.OTHER)

    /** 购物编号 */
    val shoppingId = column<String>(name = "shopping_id", jdbcType = JDBCType.OTHER)

    /** 用户类型 */
    val userType = column<String>(name = "user_type", jdbcType = JDBCType.CHAR)

    /** 应用端类型 */
    val sourceType = column<String>(name = "source_type", jdbcType = JDBCType.CHAR)

    /** 是否结束 */
    val isStopped = column<String>(name = "is_stopped", jdbcType = JDBCType.CHAR)

    /** 有效期 */
    val validDays = column<Int>(name = "valid_days", jdbcType = JDBCType.INTEGER)

    /** 创建日期 */
    val createDate = column<Date>(name = "create_date", jdbcType = JDBCType.DATE)

    /** 新增时间 */
    val createTime = column<Date>(name = "create_time", jdbcType = JDBCType.TIMESTAMP)

    /** 修改时间 */
    val updateTime = column<Date>(name = "update_time", jdbcType = JDBCType.TIMESTAMP)

    /** 是否删除 */
    val delFlag = column<String>(name = "del_flag", jdbcType = JDBCType.CHAR)
}

var eventsTable = CtEventsTable()