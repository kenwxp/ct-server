package com.cloudtimes.agent.mapper

import com.cloudtimes.agent.domain.CtAgentActivitySettlement

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper

/**
 * 代理活动结算Mapper接口
 *
 * @author 沈兵
 * @date 2023-02-17
 */
@Mapper
interface CtAgentActivitySettlementMapper : CommonCountMapper, CommonInsertMapper<CtAgentActivitySettlement>, CommonUpdateMapper {
    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @Results(
        id = "CtAgentActivitySettlementResult", value = [
            Result(column = "id", property = "id", jdbcType = JdbcType.OTHER, id = true),
            Result(column = "activity_type", property = "activityType", jdbcType = JdbcType.CHAR),
            Result(column = "activity_rule_id", property = "activityRuleId", jdbcType = JdbcType.OTHER),
            Result(column = "user_id", property = "userId", jdbcType = JdbcType.OTHER),
            Result(column = "amount", property = "amount", jdbcType = JdbcType.DECIMAL),
            Result(column = "tax_ratio", property = "taxRatio", jdbcType = JdbcType.DECIMAL),
            Result(column = "tax_amount", property = "taxAmount", jdbcType = JdbcType.DECIMAL),
            Result(column = "before_tax_amount", property = "beforeTaxAmount", jdbcType = JdbcType.DECIMAL),
            Result(column = "platform_approved_time", property = "platformApprovedTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "agent_approved_time", property = "agentApprovedTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "is_fulfilled", property = "isFulfilled", jdbcType = JdbcType.CHAR),
            Result(column = "verify_state", property = "verifyState", jdbcType = JdbcType.CHAR),
            Result(column = "state", property = "state", jdbcType = JdbcType.CHAR),
            Result(column = "del_flag", property = "delFlag", jdbcType = JdbcType.CHAR),
            Result(column = "operator", property = "operator", jdbcType = JdbcType.VARCHAR),
            Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
            Result(column = "fulfilled_date", property = "fulfilledDate", jdbcType = JdbcType.DATE),
            Result(column = "create_date", property = "createDate", jdbcType = JdbcType.DATE),
            Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "fulfilled_stores", property = "fulfilledStores", jdbcType = JdbcType.LONGVARCHAR)
        ]
    )
    fun selectMany(selectStatement: SelectStatementProvider): List<CtAgentActivitySettlement>

    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @ResultMap("CtAgentActivitySettlementResult")
    fun selectOne(selectStatement: SelectStatementProvider): CtAgentActivitySettlement?

    /**
     * 查询代理活动结算
     *
     * @param id 代理活动结算主键
     * @return 代理活动结算
     */
    fun selectCtAgentActivitySettlementById(id: String): CtAgentActivitySettlement?

    /**
     * 查询代理活动结算列表
     *
     * @param ctAgentActivitySettlement 代理活动结算
     * @return 代理活动结算集合
     */
    fun selectCtAgentActivitySettlementList(ctAgentActivitySettlement: CtAgentActivitySettlement): List<CtAgentActivitySettlement>

    /**
     * 新增代理活动结算
     *
     * @param ctAgentActivitySettlement 代理活动结算
     * @return 结果
     */
    fun insertCtAgentActivitySettlement(ctAgentActivitySettlement: CtAgentActivitySettlement): Int

    /**
     * 修改代理活动结算
     *
     * @param ctAgentActivitySettlement 代理活动结算
     * @return 结果
     */
    fun updateCtAgentActivitySettlement(ctAgentActivitySettlement: CtAgentActivitySettlement): Int
}
