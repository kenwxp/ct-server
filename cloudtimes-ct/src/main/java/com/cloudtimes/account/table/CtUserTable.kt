package com.cloudtimes.account.table


import java.math.BigDecimal
import java.sql.JDBCType
import java.util.*
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

class CtUserTable : AliasableSqlTable<CtUserTable>("ct_user", ::CtUserTable) {
    val id = column<Object>(name = "id", jdbcType = JDBCType.OTHER)

    val account = column<String>(name = "account", jdbcType = JDBCType.VARCHAR)

    val wxOpenId = column<String>(name = "wx_open_id", jdbcType = JDBCType.VARCHAR)

    val wxUnionId = column<String>(name = "wx_union_id", jdbcType = JDBCType.VARCHAR)

    val wxAvatar = column<String>(name = "wx_avatar", jdbcType = JDBCType.VARCHAR)

    val password = column<String>(name = "password", jdbcType = JDBCType.VARCHAR)

    val mobile = column<String>(name = "mobile", jdbcType = JDBCType.VARCHAR)

    val moneyAmount = column<BigDecimal>(name = "money_amount", jdbcType = JDBCType.DECIMAL)

    val scoreAmount = column<BigDecimal>(name = "score_amount", jdbcType = JDBCType.DECIMAL)

    val totalWithdrawal = column<BigDecimal>(name = "total_withdrawal", jdbcType = JDBCType.DECIMAL)

    val creditScore = column<Int>(name = "credit_score", jdbcType = JDBCType.INTEGER)

    val nickName = column<String>(name = "nick_name", jdbcType = JDBCType.VARCHAR)

    val realName = column<String>(name = "real_name", jdbcType = JDBCType.VARCHAR)

    val sex = column<String>(name = "sex", jdbcType = JDBCType.CHAR)

    val birthday = column<Date>(name = "birthday", jdbcType = JDBCType.DATE)

    val isReal = column<String>(name = "is_real", jdbcType = JDBCType.CHAR)

    val violationCount = column<Int>(name = "violation_count", jdbcType = JDBCType.INTEGER)

    val idNo = column<String>(name = "id_no", jdbcType = JDBCType.VARCHAR)

    val registerStoreId = column<Object>(name = "register_store_id", jdbcType = JDBCType.OTHER)

    val isAgent = column<String>(name = "is_agent", jdbcType = JDBCType.CHAR)

    val isShopBoss = column<String>(name = "is_shop_boss", jdbcType = JDBCType.CHAR)

    val lastLoginIp = column<String>(name = "last_login_ip", jdbcType = JDBCType.VARCHAR)

    val lastLoginTime = column<Date>(name = "last_login_time", jdbcType = JDBCType.TIMESTAMP)

    val createBossTime = column<Date>(name = "create_boss_time", jdbcType = JDBCType.TIMESTAMP)

    val createAgentTime = column<Date>(name = "create_agent_time", jdbcType = JDBCType.TIMESTAMP)

    val agentState = column<String>(name = "agent_state", jdbcType = JDBCType.CHAR)

    val agentType = column<String>(name = "agent_type", jdbcType = JDBCType.CHAR)

    val bossState = column<String>(name = "boss_state", jdbcType = JDBCType.CHAR)

    val customerState = column<String>(name = "customer_state", jdbcType = JDBCType.CHAR)

    val operator = column<String>(name = "operator", jdbcType = JDBCType.VARCHAR)

    val createDate = column<Date>(name = "create_date", jdbcType = JDBCType.DATE)

    val createTime = column<Date>(name = "create_time", jdbcType = JDBCType.TIMESTAMP)

    val updateTime = column<Date>(name = "update_time", jdbcType = JDBCType.TIMESTAMP)

    val delFlag = column<String>(name = "del_flag", jdbcType = JDBCType.CHAR)
}

val userTable = CtUserTable()