package com.cloudtimes.hardwaredevice.mapper.provider

import com.cloudtimes.common.enums.ShopBuildState
import com.cloudtimes.hardwaredevice.table.storeTable
import org.apache.ibatis.annotations.Select
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider

object CTStoreProvider {
    /** 根据ids查询上线的店铺 */
    fun selectOnlineStoresByIds(ids: List<String>): SelectStatementProvider {
        return with(storeTable) {
            select(storeTable.allColumns()) {
                from(storeTable)
                where { id isIn ids }
                and { buildState isEqualTo ShopBuildState.Online.code }
            }
        }
    }

    /** 查询上线，并且有代理的店铺 */
    fun selectOnlineStoresWithAgent(): SelectStatementProvider {
        return with(storeTable) {
            select(storeTable.allColumns()) {
                from(storeTable)
                where { buildState isEqualTo ShopBuildState.Online.code }
                and { agentId.isNotNull() }
            }
        }
    }
}