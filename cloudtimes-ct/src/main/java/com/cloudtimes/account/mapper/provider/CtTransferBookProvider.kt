package com.cloudtimes.account.mapper.provider

import com.cloudtimes.account.domain.CtTransferBook
import com.cloudtimes.account.table.CtTransferBookTable
import com.cloudtimes.account.table.CtUserTable

import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select
import org.mybatis.dynamic.sql.util.kotlin.elements.sortColumn
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.insert

object CtTransferBookProvider {
    private val userTable1: CtUserTable = CtUserTable().withAlias("u1")
    private val userTable2: CtUserTable = CtUserTable().withAlias("u2")
    private val transferTable: CtTransferBookTable = CtTransferBookTable().withAlias("tsf")

    fun insertOne(row: CtTransferBook) : InsertStatementProvider<CtTransferBook> {
        return with(transferTable) {
            insert(row) {
                into(transferTable)
                map(id) toProperty "id"
                map(payer) toProperty "payer"
                map(payee) toProperty "payee"
                map(yearMonth) toProperty "yearMonth"
                map(amount) toProperty "amount"
                map(remark) toProperty "remark"
            }
        }
    }

    fun selectTransferRecordsStmt(userId: String) : SelectStatementProvider {
        return select(
            transferTable.id.`as`("transferId"),
            transferTable.createTime.`as`("transferTime"),
            transferTable.remark.`as`("transferRemark"),
            transferTable.amount.`as`("transferAmount"),

            userTable1.id.`as`("payerUserId"),
            userTable1.nickName.`as`("payerNickName"),
            userTable1.realName.`as`("payerRealName"),
            userTable1.mobile.`as`("payerMobile"),

            userTable2.id.`as`("payeeUserId"),
            userTable2.nickName.`as`("payeeNickName"),
            userTable2.realName.`as`("payeeRealName"),
            userTable2.mobile.`as`("payeeMobile"),
        )  {
            from (transferTable)
            join(userTable1) { on(userTable1.id) equalTo transferTable.payer}
            join(userTable2) { on(userTable2.id) equalTo transferTable.payee}
            where {
                transferTable.payer isEqualTo userId
            }
            orderBy(sortColumn("transferTime").descending())
        }
    }
}