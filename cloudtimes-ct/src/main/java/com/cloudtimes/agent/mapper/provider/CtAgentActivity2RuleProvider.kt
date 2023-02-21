package com.cloudtimes.agent.mapper.provider

import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider

import com.cloudtimes.agent.dto.request.ActivityDetailRequest
import com.cloudtimes.agent.table.CtAgentActivity2RuleTable
import com.cloudtimes.resources.table.CtRegionTable

object CtAgentActivity2RuleProvider {
    private val rule2Table = CtAgentActivity2RuleTable().withAlias("rule2")
    private val regionTable1 = CtRegionTable().withAlias("r1");
    private val regionTable2 = CtRegionTable().withAlias("r2");

    fun selectAgentActivityDetailStmt(request: ActivityDetailRequest): SelectStatementProvider {
        return select(rule2Table.allColumns(),
            regionTable1.regionName, regionTable2.regionName.`as`("parent_region_name")
        ) {
            from(rule2Table)
            leftJoin(regionTable1) { on(rule2Table.regionCode) equalTo regionTable1.regionCode }
            leftJoin(regionTable2) { on(regionTable1.parentRegionCode) equalTo regionTable2.regionCode }
            where { rule2Table.activityId isEqualTo request.activityId!! }
        }
    }
}