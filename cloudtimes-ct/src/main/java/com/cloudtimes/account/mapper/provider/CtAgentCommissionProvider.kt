package com.cloudtimes.account.mapper.provider


import com.cloudtimes.account.domain.CtAgentCommission
import com.cloudtimes.account.dto.request.UpdateSubUserCommissionRequest
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import com.cloudtimes.account.table.commissionTable
import com.cloudtimes.account.table.userTable
import org.mybatis.dynamic.sql.insert.render.GeneralInsertStatementProvider
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.insertInto
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.update
import java.util.Date

object CtAgentCommissionProvider {
    fun selectByUserId(userId: String): SelectStatementProvider {
        return select(commissionTable.allColumns()) {
            from(commissionTable)
            where { commissionTable.userId isEqualTo userId }
        }
    }

    fun insertOneWithParentConfig(
        parentCommission: CtAgentCommission,
        childUserId: String
    ): GeneralInsertStatementProvider {
        return with(commissionTable) {
            insertInto(commissionTable) {
                set(userId) toValue childUserId
                set(parentUserId) toValue parentCommission.userId!!
                set(taxRatio) toValue parentCommission.taxRatio!!
            }
        }
    }

    fun updateSubUserCommission(request: UpdateSubUserCommissionRequest): UpdateStatementProvider {
        return update(commissionTable) {
            set(commissionTable.costPrice) equalTo request.detail!!.costPrice!!
            where { commissionTable.userId isEqualTo request.subUserId!! }
            and { commissionTable.updateTime isEqualTo Date()}
        }
    }
}
