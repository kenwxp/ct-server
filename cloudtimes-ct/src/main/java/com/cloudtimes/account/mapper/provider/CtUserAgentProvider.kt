package com.cloudtimes.account.mapper.provider

import com.cloudtimes.account.table.ctUserAgentTable
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select

import org.mybatis.dynamic.sql.select.render.SelectStatementProvider

object CtUserAgentProvider {
    fun selectById(id: String): SelectStatementProvider {
        return with(ctUserAgentTable) {
            select(ctUserAgentTable.allColumns()) {
                from(ctUserAgentTable)
                where {
                    userId isEqualTo id
                }
            }
        }
    }
}