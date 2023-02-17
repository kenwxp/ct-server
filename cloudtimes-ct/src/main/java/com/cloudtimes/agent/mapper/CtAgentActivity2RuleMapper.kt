package com.cloudtimes.agent.mapper

import com.cloudtimes.agent.domain.CtAgentActivity2Rule

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
 * 代理活动2规则Mapper接口
 *
 * @author 沈兵
 * @date 2023-02-17
 */
@Mapper
interface CtAgentActivity2RuleMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<CtAgentActivity2Rule>,
    CommonUpdateMapper {
    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @Results(
        id = "CtAgentActivity2RuleResult", value = [
            Result(column = "id", property = "id", jdbcType = JdbcType.OTHER, id = true),
            Result(column = "activity_id", property = "activityId", jdbcType = JdbcType.OTHER),
            Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            Result(column = "activity_type", property = "activityType", jdbcType = JdbcType.CHAR),
            Result(column = "region", property = "region", jdbcType = JdbcType.OTHER),
            Result(column = "store_count", property = "storeCount", jdbcType = JdbcType.INTEGER),
            Result(column = "used_store_count", property = "usedStoreCount", jdbcType = JdbcType.INTEGER),
            Result(column = "tax_ratio", property = "taxRatio", jdbcType = JdbcType.DECIMAL),
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
    fun selectMany(selectStatement: SelectStatementProvider): List<CtAgentActivity2Rule>

    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @ResultMap("CtAgentActivity2RuleResult")
    fun selectOne(selectStatement: SelectStatementProvider): CtAgentActivity2Rule?

    /**
     * 查询代理活动2规则
     *
     * @param id 代理活动2规则主键
     * @return 代理活动2规则
     */
    fun selectCtAgentActivity2RuleById(id: String): CtAgentActivity2Rule?

    /**
     * 查询代理活动2规则列表
     *
     * @param ctAgentActivity2Rule 代理活动2规则
     * @return 代理活动2规则集合
     */
    fun selectCtAgentActivity2RuleList(ctAgentActivity2Rule: CtAgentActivity2Rule): List<CtAgentActivity2Rule>

    /**
     * 新增代理活动2规则
     *
     * @param ctAgentActivity2Rule 代理活动2规则
     * @return 结果
     */
    fun insertCtAgentActivity2Rule(ctAgentActivity2Rule: CtAgentActivity2Rule): Int

    /**
     * 修改代理活动2规则
     *
     * @param ctAgentActivity2Rule 代理活动2规则
     * @return 结果
     */
    fun updateCtAgentActivity2Rule(ctAgentActivity2Rule: CtAgentActivity2Rule): Int

    /**
     * 删除代理活动2规则
     *
     * @param id 代理活动2规则主键
     * @return 结果
     */
    fun deleteCtAgentActivity2RuleById(id: String): Int

    /**
     * 批量删除代理活动2规则
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    fun deleteCtAgentActivity2RuleByIds(ids: Array<String>): Int
}
