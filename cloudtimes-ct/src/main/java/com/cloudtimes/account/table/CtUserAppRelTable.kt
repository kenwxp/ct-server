package com.cloudtimes.account.table

import org.mybatis.dynamic.sql.util.kotlin.elements.column
import java.sql.JDBCType

import org.mybatis.dynamic.sql.AliasableSqlTable
import java.time.LocalDate

class CtUserAppRelTable : AliasableSqlTable<CtUserAppRelTable>("ct_user_app_rel", ::CtUserAppRelTable) {
    val userId = column<String>(name = "user_id", jdbcType = JDBCType.OTHER)

    val appType = column<String>(name = "app_type", jdbcType = JDBCType.VARCHAR)

    val appUserId = column<String>(name = "app_user_id", jdbcType = JDBCType.VARCHAR)

    val createTime = column<LocalDate>(name = "create_time", jdbcType = JDBCType.TIMESTAMP)

    val updateTime = column<LocalDate>(name = "update_time", jdbcType = JDBCType.TIMESTAMP)
}