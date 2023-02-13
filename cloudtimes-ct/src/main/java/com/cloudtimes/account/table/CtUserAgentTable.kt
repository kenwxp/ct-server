package com.cloudtimes.account.table

import java.math.BigDecimal
import java.sql.JDBCType
import java.util.*
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

class CtUserAgentTable : AliasableSqlTable<CtUserAgentTable>("ct_user_agent", ::CtUserAgentTable) {
    /** 用户编号 */
    val userId = column<String>("user_id", JDBCType.OTHER)

    /** 用户昵称 */
    val nickName = column<String>("nick_name", JDBCType.VARCHAR)

    /** 代理类型 */
    val agentType = column<String>("agent_type", JDBCType.CHAR)

    /** 上级代理编号 */
    val parentUserId = column<String>("parent_user_id", JDBCType.OTHER)

    /** 代理现金余额(可提现金额) */
    val cashAmount = column<BigDecimal>("cash_amount", JDBCType.DECIMAL)

    /** 代理累计已提现 */
    val totalWithdrawal = column<BigDecimal>("total_withdrawal", JDBCType.DECIMAL)

    /** 累计销售提成(累计产品销售佣金) */
    val totalSalesReward = column<BigDecimal>("total_sales_reward", JDBCType.DECIMAL)

    /** 累计活动提成(平台活动奖励) */
    val totalActivityReward = column<BigDecimal>("total_activity_reward", JDBCType.DECIMAL)

    /** 累计分润提成(应收分成) */
    val totalDividend = column<BigDecimal>("total_dividend", JDBCType.DECIMAL)

    /** 累计下级贡献 */
    val totalTributes = column<BigDecimal>("total_tributes", JDBCType.DECIMAL)

    /** 累计下级贡献分润 */
    val totalTributesDividend = column<BigDecimal>("total_tributes_dividend", JDBCType.DECIMAL)

    /** 累计下级贡献佣金 */
    val totalTributesCommission = column<BigDecimal>("total_tributes_commission", JDBCType.DECIMAL)

    /** 备注 */
    val remark = column<String>("remark", JDBCType.VARCHAR)

    /** 创建日期 */
    val createDate = column<Date>("create_date", JDBCType.DATE)

    /** 创建时间 */
    val createTime = column<Date>("create_time", JDBCType.TIMESTAMP)

    /** 修改时间 */
    val updateTime = column<Date>("update_time", JDBCType.TIMESTAMP)

    /** 是否删除 */
    val delFlag = column<String>("del_flag", JDBCType.CHAR)

    /** 代理区域 */
    val agentArea = column<String>("agent_area", JDBCType.LONGVARCHAR)
}

val ctUserAgentTable = CtUserAgentTable()