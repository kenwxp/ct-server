package com.cloudtimes.account.table

import com.cloudtimes.account.domain.CtActivityRegion
import java.sql.JDBCType

import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

class CtActivityRegionTable : AliasableSqlTable<CtActivityRegionTable>("ct_activity_region", ::CtActivityRegionTable) {
    val activityId = column<String>(name = "activity_id", jdbcType = JDBCType.OTHER)
    val regionCode = column<String>(name = "region_code", jdbcType = JDBCType.VARCHAR)
}

val activityRegionTable = CtActivityRegion()