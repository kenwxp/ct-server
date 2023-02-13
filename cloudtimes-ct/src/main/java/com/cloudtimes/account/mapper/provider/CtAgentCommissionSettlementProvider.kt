package com.cloudtimes.account.mapper.provider

import java.util.Date

import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.update
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider
import com.cloudtimes.account.table.commissionSettlementTable
import com.cloudtimes.common.enums.YesNoState

object CtAgentCommissionSettlementProvider {
    /** 查找还没有上线的店铺结算记录 */
    fun selectNotOnlineStores(): SelectStatementProvider {
        return with(commissionSettlementTable) {
            select(commissionSettlementTable.allColumns()) {
                from(commissionSettlementTable)
                where { isStoreOnline isEqualTo YesNoState.No.code }
            }
        }
    }

    /** 更新上线的店铺结算记录 */
    fun updateOnlineStores(storeIds: List<String>): UpdateStatementProvider {
        return with(commissionSettlementTable) {
            update(commissionSettlementTable) {
                set(isStoreOnline) equalTo YesNoState.Yes.code
                set(updateTime) equalTo Date()
                where { storeId isIn storeIds }
            }
        }
    }
}
