package com.cloudtimes.account.table

import java.math.BigDecimal
import java.sql.JDBCType
import java.util.Date
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

class CtAgentActivitySettlementTable : AliasableSqlTable<CtAgentActivitySettlementTable>(
    "ct_agent_activity_settlement",
    ::CtAgentActivitySettlementTable
) {

    /** 编号 */
    val id = column<String>(name = "id", jdbcType = JDBCType.OTHER)

    /** 活动编号 */
    val activityId = column<String>(name = "activity_id", jdbcType = JDBCType.OTHER)

    /** 代理用户编号 */
    val userId = column<String>(name = "user_id", jdbcType = JDBCType.OTHER)

    /** 操作额度/实际到账金额 */
    val amount = column<BigDecimal>(name = "amount", jdbcType = JDBCType.DECIMAL)

    /** 手续费费率 */
    val taxRatio = column<BigDecimal>(name = "tax_ratio", jdbcType = JDBCType.DECIMAL)

    /** 手续费金额 */
    val taxAmount = column<BigDecimal>(name = "tax_amount", jdbcType = JDBCType.DECIMAL)

    /** 稅前结算金额 */
    val beforeTaxAmount = column<BigDecimal>(name = "before_tax_amount", jdbcType = JDBCType.DECIMAL)

    /** 平台审核时间 */
    val platformApprovedTime = column<Date>(name = "platform_approved_time", jdbcType = JDBCType.TIMESTAMP)

    /** 代理审核时间 */
    val agentApprovedTime = column<Date>(name = "agent_approved_time", jdbcType = JDBCType.TIMESTAMP)

    /** 是否已达成 */
    val isFulfilled = column<String>(name = "is_fulfilled", jdbcType = JDBCType.CHAR)

    /** 代理是否审核 */
    val isAgentOk = column<String>(name = "is_agent_ok", jdbcType = JDBCType.CHAR)

    /** 平台是否审核 */
    val isPlatformOk = column<String>(name = "is_platform_ok", jdbcType = JDBCType.CHAR)

    /** 结算状态 */
    val state = column<String>(name = "state", jdbcType = JDBCType.CHAR)

    /** 是否删除 */
    val delFlag = column<String>(name = "del_flag", jdbcType = JDBCType.CHAR)

    /** 操作管理员 */
    val operator = column<String>(name = "operator", jdbcType = JDBCType.VARCHAR)

    /** 备注 */
    val remark = column<String>(name = "remark", jdbcType = JDBCType.VARCHAR)

    /** 达成日期 */
    val fulfilledDate = column<Date>(name = "fulfilled_date", jdbcType = JDBCType.DATE)

    /** 创建日期 */
    val createDate = column<Date>(name = "create_date", jdbcType = JDBCType.DATE)

    /** 创建时间 */
    val createTime = column<Date>(name = "create_time", jdbcType = JDBCType.TIMESTAMP)

    /** 修改时间 */
    val updateTime = column<Date>(name = "update_time", jdbcType = JDBCType.TIMESTAMP)
}
