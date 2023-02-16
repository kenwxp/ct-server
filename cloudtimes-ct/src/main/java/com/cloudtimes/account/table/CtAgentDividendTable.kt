package com.cloudtimes.account.table

import java.math.BigDecimal
import java.sql.JDBCType
import java.util.Date
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

class CtAgentDividendTable : AliasableSqlTable<CtAgentDividendTable>("ct_agent_dividend", ::CtAgentDividendTable) {
    /** 编号 */
    val id = column<String>(name = "id", jdbcType = JDBCType.OTHER)

    /** 代理用户编号 */
    val userId = column<String>(name = "user_id", jdbcType = JDBCType.OTHER)

    /** 上级代理用户编号 */
    val parentUserId = column<String>(name = "parent_user_id", jdbcType = JDBCType.OTHER)

    /** 比较类型 */
    val compareType = column<String>(name = "compare_type", jdbcType = JDBCType.VARCHAR)

    /** 营收金额 */
    val billAmount = column<BigDecimal>(name = "bill_amount", jdbcType = JDBCType.DECIMAL)

    /** 提成比例 */
    val dividendRatio = column<BigDecimal>(name = "dividend_ratio", jdbcType = JDBCType.DECIMAL)

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

val dividendTable = CtAgentDividendTable()