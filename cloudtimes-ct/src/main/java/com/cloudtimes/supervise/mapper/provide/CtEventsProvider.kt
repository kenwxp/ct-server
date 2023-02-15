package com.cloudtimes.supervise.mapper.provide

import com.cloudtimes.supervise.table.eventsTable
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider

object CtEventsProvider {
    fun byReceiver(receiver: String): SelectStatementProvider {
        return select(eventsTable.allColumns()) {
            from(eventsTable)
            where { eventsTable.receiver isEqualTo receiver }
        }
    }
}
