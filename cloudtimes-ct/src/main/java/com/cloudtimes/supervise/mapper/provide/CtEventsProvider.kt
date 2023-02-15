package com.cloudtimes.supervise.mapper.provide

import com.cloudtimes.supervise.table.eventsTable
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider

object CtEventsProvider {
    fun byReceiver(receiver: String): SelectStatementProvider {
        return select(eventsTable.allColumns()) {
            from(eventsTable)
            where { eventsTable.receiver isEqualTo receiver }
            orderBy(eventsTable.createTime.descending())
        }
    }

    fun byReceiverAndMessageType(receiver: String, msgType: String): SelectStatementProvider {
        return select(eventsTable.allColumns()) {
            from(eventsTable)
            where { eventsTable.receiver isEqualTo receiver }
            and { eventsTable.subType isEqualTo msgType }
            orderBy(eventsTable.createTime.descending())
        }
    }

    fun byReceiverAndMessageTypeLimitOne(receiver: String, msgType: String): SelectStatementProvider {
        return select(eventsTable.allColumns()) {
            from(eventsTable)
            where { eventsTable.receiver isEqualTo receiver }
            and { eventsTable.subType isEqualTo msgType }
            orderBy(eventsTable.createTime.descending())
            limit(1)
        }
    }
}
