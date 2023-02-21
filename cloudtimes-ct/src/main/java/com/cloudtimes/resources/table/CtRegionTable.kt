package com.cloudtimes.resources.table

import java.sql.JDBCType
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column
import java.time.LocalDateTime

class CtRegionTable : AliasableSqlTable<CtRegionTable>("ct_region", ::CtRegionTable) {
    val id = column<String>(name = "id", jdbcType = JDBCType.OTHER)

    val regionName = column<String>(name = "region_name", jdbcType = JDBCType.VARCHAR)

    val regionShortName = column<String>(name = "region_short_name", jdbcType = JDBCType.VARCHAR)

    val regionCode = column<String>(name = "region_code", jdbcType = JDBCType.VARCHAR)

    val parentRegionCode = column<String>(name = "parent_region_code", jdbcType = JDBCType.OTHER)

    val regionLevel = column<Int>(name = "region_level", jdbcType = JDBCType.INTEGER)

    val createTime = column<LocalDateTime>(name = "create_time", jdbcType = JDBCType.TIMESTAMP)

    val updateTime = column<LocalDateTime>(name = "update_time", jdbcType = JDBCType.TIMESTAMP)

    val delFlag = column<String>(name = "del_flag", jdbcType = JDBCType.CHAR)
}
