package com.cloudtimes.account.table

import java.math.BigDecimal
import java.sql.JDBCType
import java.util.Date
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column


class CtAgentActivity1Table : AliasableSqlTable<CtAgentActivity1Table>("ct_agent_activity1", ::CtAgentActivity1Table) {
    /** 编号 */
    val id = column<String>(name = "id", jdbcType = JDBCType.OTHER)

    /** 标题 */
    val title = column<String>(name = "title", jdbcType = JDBCType.VARCHAR)

    /** 内容 */
    val content = column<String>(name = "content", jdbcType = JDBCType.VARCHAR)

    /** 新开店数量 */
    val storeCount = column<Int>(name = "store_count", jdbcType = JDBCType.INTEGER)

    /** 奖励金额 */
    val rewardMoney = column<BigDecimal>(name = "reward_money", jdbcType = JDBCType.DECIMAL)

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