package com.cloudtimes.account.mapper.provider


import com.cloudtimes.account.table.CtUserAppRelTable
import com.cloudtimes.common.enums.AppType
import org.mybatis.dynamic.sql.insert.render.GeneralInsertStatementProvider
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.insertInto

object CtUserAppRelProvider {
    private val userAppTable = CtUserAppRelTable()

    fun insertStmt(userId: String, appType: AppType, appUserId: String): GeneralInsertStatementProvider {
        return insertInto(userAppTable) {
            set(userAppTable.userId) toValue userId
            set(userAppTable.appType) toValue appType.code
            set(userAppTable.appUserId) toValue appUserId
        }
    }

    fun selectOneStmt(userId: String, appType: AppType): SelectStatementProvider {
        return select(userAppTable.allColumns()) {
            from(userAppTable)
            where { userAppTable.userId isEqualTo userId }
            and { userAppTable.appType isEqualTo appType.code }
        }
    }
}