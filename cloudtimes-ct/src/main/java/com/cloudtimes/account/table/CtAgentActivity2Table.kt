package com.cloudtimes.account.table

import java.math.BigDecimal
import java.sql.JDBCType
import java.util.Date
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column


class CtAgentActivity2Table : AliasableSqlTable<CtAgentActivity2Table>("ct_agent_activity2", ::CtAgentActivity2Table) {
    /** 编号 */
    val id = column<String>(name = "id", jdbcType = JDBCType.OTHER)

    /** 标题 */
    val title = column<String>(name = "title", jdbcType = JDBCType.VARCHAR)

    /** 内容 */
    val content = column<String>(name = "content", jdbcType = JDBCType.VARCHAR)

    /** 活动类型 */
    val activityType = column<String>(name = "activity_type", jdbcType = JDBCType.CHAR)

    /** 奖励门店数 */
    val rewardShopCount = column<Int>(name = "reward_shop_count", jdbcType = JDBCType.INTEGER)

    /** 已奖励门店数 */
    val usedShopCount = column<Int>(name = "used_shop_count", jdbcType = JDBCType.INTEGER)

    /** 手续费费率 */
    val taxRatio = column<BigDecimal>(name = "tax_ratio", jdbcType = JDBCType.DECIMAL)

    /** 结算时长条件 */
    val timeSpan = column<Int>(name = "time_span", jdbcType = JDBCType.INTEGER)

    /** 操作管理员 */
    val operator = column<String>(name = "operator", jdbcType = JDBCType.VARCHAR)

    /** 是否启用 */
    val isEnabled = column<String>(name = "is_enabled", jdbcType = JDBCType.CHAR)

    /** 活动状态 */
    val state = column<String>(name = "state", jdbcType = JDBCType.CHAR)

    /** 是否删除 */
    val delFlag = column<String>(name = "del_flag", jdbcType = JDBCType.CHAR)

    /** 开始时间 */
    val startTime = column<Date>(name = "start_time", jdbcType = JDBCType.TIMESTAMP)

    /** 结束时间 */
    val endTime = column<Date>(name = "end_time", jdbcType = JDBCType.TIMESTAMP)

    /** 创建时间 */
    val createTime = column<Date>(name = "create_time", jdbcType = JDBCType.TIMESTAMP)

    /** 修改时间 */
    val updateTime = column<Date>(name = "update_time", jdbcType = JDBCType.TIMESTAMP)
}

val activity2Table = CtAgentActivity2Table()