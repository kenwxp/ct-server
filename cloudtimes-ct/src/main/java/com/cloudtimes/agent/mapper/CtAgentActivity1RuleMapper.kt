package com.cloudtimes.agent.mapper

import com.cloudtimes.agent.domain.CtAgentActivity1Rule

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
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper

/**
 * 代理活动1规则Mapper接口
 *
 * @author 沈兵
 * @date 2023-02-17
 */
@Mapper
interface CtAgentActivity1RuleMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<CtAgentActivity1Rule>,
    CommonUpdateMapper {
    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @Results(
        id = "CtAgentActivity1RuleResult", value = [
            Result(column = "id", property = "id", jdbcType = JdbcType.OTHER, id = true),
            Result(column = "activity_id", property = "activityId", jdbcType = JdbcType.OTHER),
            Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            Result(column = "activity_type", property = "activityType", jdbcType = JdbcType.CHAR),
            Result(column = "store_count", property = "storeCount", jdbcType = JdbcType.INTEGER),
            Result(column = "reward_money", property = "rewardMoney", jdbcType = JdbcType.DECIMAL),
            Result(column = "tax_ratio", property = "taxRatio", jdbcType = JdbcType.DECIMAL),
            Result(column = "time_span", property = "timeSpan", jdbcType = JdbcType.INTEGER),
            Result(column = "operator", property = "operator", jdbcType = JdbcType.VARCHAR),
            Result(column = "is_enabled", property = "isEnabled", jdbcType = JdbcType.CHAR),
            Result(column = "state", property = "state", jdbcType = JdbcType.CHAR),
            Result(column = "del_flag", property = "delFlag", jdbcType = JdbcType.CHAR),
            Result(column = "start_time", property = "startTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "end_time", property = "endTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
        ]
    )
    fun selectMany(selectStatement: SelectStatementProvider): List<CtAgentActivity1Rule>

    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @ResultMap("CtAgentActivity1RuleResult")
    fun selectOne(selectStatement: SelectStatementProvider): CtAgentActivity1Rule?

    /**
     * 查询代理活动1规则
     *
     * @param id 代理活动1规则主键
     * @return 代理活动1规则
     */
    fun selectCtAgentActivity1RuleById(id: String): CtAgentActivity1Rule?

    /**
     * 查询代理活动1规则列表
     *
     * @param ctAgentActivity1Rule 代理活动1规则
     * @return 代理活动1规则集合
     */
    fun selectCtAgentActivity1RuleList(ctAgentActivity1Rule: CtAgentActivity1Rule): List<CtAgentActivity1Rule>

    /**
     * 新增代理活动1规则
     *
     * @param ctAgentActivity1Rule 代理活动1规则
     * @return 结果
     */
    fun insertCtAgentActivity1Rule(ctAgentActivity1Rule: CtAgentActivity1Rule): Int

    /**
     * 修改代理活动1规则
     *
     * @param ctAgentActivity1Rule 代理活动1规则
     * @return 结果
     */
    fun updateCtAgentActivity1Rule(ctAgentActivity1Rule: CtAgentActivity1Rule): Int

    /**
     * 删除代理活动1规则
     *
     * @param id 代理活动1规则主键
     * @return 结果
     */
    fun deleteCtAgentActivity1RuleById(id: String): Int

    /**
     * 批量删除代理活动1规则
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    fun deleteCtAgentActivity1RuleByIds(ids: Array<String>): Int
}
