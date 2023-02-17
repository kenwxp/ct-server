package com.cloudtimes.agent.table

import java.math.BigDecimal
import java.sql.JDBCType
import java.util.Date
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

class CtAgentCommissionTable : AliasableSqlTable<CtAgentCommissionTable>("ct_agent_commission", ::CtAgentCommissionTable) {
    /** 编号 */
    val id = column<String>(name = "id", jdbcType = JDBCType.OTHER)

    /** 代理编号 */
    val userId = column<String>(name = "user_id", jdbcType = JDBCType.OTHER)

    /** 上级代理编号 */
    val parentUserId = column<String>(name = "parent_user_id", jdbcType = JDBCType.OTHER)

    /** 成本价格 */
    val costPrice = column<BigDecimal>(name = "cost_price", jdbcType = JDBCType.DECIMAL)

    /** 手续费费率 */
    val taxRatio = column<BigDecimal>(name = "tax_ratio", jdbcType = JDBCType.DECIMAL)

    /** 操作管理员 */
    val operator = column<String>(name = "operator", jdbcType = JDBCType.VARCHAR)

    /** 备注 */
    val remark = column<String>(name = "remark", jdbcType = JDBCType.VARCHAR)

    /** 是否删除 */
    val delFlag = column<String>(name = "del_flag", jdbcType = JDBCType.CHAR)

    /** 创建日期 */
    val createDate = column<Date>(name = "create_date", jdbcType = JDBCType.DATE)

    /** 创建时间 */
    val createTime = column<Date>(name = "create_time", jdbcType = JDBCType.TIMESTAMP)

    /** 修改时间 */
    val updateTime = column<Date>(name = "update_time", jdbcType = JDBCType.TIMESTAMP)
}

var commissionTable = CtAgentCommissionTable()
