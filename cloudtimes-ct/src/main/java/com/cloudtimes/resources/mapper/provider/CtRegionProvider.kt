package com.cloudtimes.resources.mapper.provider

import com.cloudtimes.resources.table.CtRegionTable
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider

object CtRegionProvider {
    private val regionTable = CtRegionTable();

    fun selectAllRegions() : SelectStatementProvider {
        return select(regionTable.allColumns()) {
            from(regionTable)
            orderBy(regionTable.regionCode)
        }
    }
}