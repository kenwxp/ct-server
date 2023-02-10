package com.cloudtimes.account.table

import java.lang.Object
import java.math.BigDecimal
import java.sql.JDBCType
import java.util.Date
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

class CtAgentCommissionSettlementTable : AliasableSqlTable<CtAgentCommissionSettlementTable>("ct_agent_commission_settlement", ::CtAgentCommissionSettlementTable) {
    /* 编号 */
    val id = column<Object>(name = "id", jdbcType = JDBCType.OTHER)

    /* 门店编号 */
    val storeId = column<Object>(name = "store_id", jdbcType = JDBCType.OTHER)

    /* 代理会员编号 */
    val userId = column<Object>(name = "user_id", jdbcType = JDBCType.OTHER)

    /* 系统价格 */
    val systemPrice = column<BigDecimal>(name = "system_price", jdbcType = JDBCType.DECIMAL)

    /* 预计结算时间 */
    val settleTime = column<Date>(name = "settle_time", jdbcType = JDBCType.TIMESTAMP)

    /* 代理是否审核 */
    val isAgentOk = column<String>(name = "is_agent_ok", jdbcType = JDBCType.CHAR)

    /* 平台是否审核 */
    val isPlatformOk = column<String>(name = "is_platform_ok", jdbcType = JDBCType.CHAR)

    /* 结算状态 */
    val state = column<String>(name = "state", jdbcType = JDBCType.CHAR)

    /* 备注 */
    val remark = column<String>(name = "remark", jdbcType = JDBCType.VARCHAR)

    /* 是否删除 */
    val delFlag = column<String>(name = "del_flag", jdbcType = JDBCType.CHAR)

    /* 平台审核时间 */
    val platformApprovedTime = column<Date>(name = "platform_approved_time", jdbcType = JDBCType.TIMESTAMP)

    /* 代理审核时间 */
    val agentApprovedTime = column<Date>(name = "agent_approved_time", jdbcType = JDBCType.TIMESTAMP)

    /* 创建日期 */
    val createDate = column<Date>(name = "create_date", jdbcType = JDBCType.DATE)

    /* 创建时间 */
    val createTime = column<Date>(name = "create_time", jdbcType = JDBCType.TIMESTAMP)

    /* 修改时间 */
    val updateTime = column<Date>(name = "update_time", jdbcType = JDBCType.TIMESTAMP)
}