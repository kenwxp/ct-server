package com.cloudtimes.agent.mapper

import com.cloudtimes.agent.domain.CtAgentActivityRuleStoreRel

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
 * 代理活动规则与店铺关系Mapper接口
 *
 * @author 沈兵
 * @date 2023-02-17
 */
@Mapper
interface CtAgentActivityRuleStoreRelMapper : CommonCountMapper, CommonDeleteMapper,
    CommonInsertMapper<CtAgentActivityRuleStoreRel>, CommonUpdateMapper {
    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @Results(
        id = "CtAgentActivityRuleStoreRelResult", value = [
            Result(column = "id", property = "id", jdbcType = JdbcType.OTHER, id = true),
            Result(column = "activity_rule_id", property = "activityRuleId", jdbcType = JdbcType.OTHER),
            Result(column = "store_id", property = "storeId", jdbcType = JdbcType.OTHER),
            Result(column = "store_online_date", property = "storeOnlineDate", jdbcType = JdbcType.DATE),
            Result(column = "region_code", property = "regionCode", jdbcType = JdbcType.VARCHAR),
            Result(column = "parent_agent_id", property = "parentAgentId", jdbcType = JdbcType.OTHER),
            Result(column = "agent_id", property = "agentId", jdbcType = JdbcType.OTHER),
            Result(column = "del_flag", property = "delFlag", jdbcType = JdbcType.CHAR),
            Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
        ]
    )
    fun selectMany(selectStatement: SelectStatementProvider): List<CtAgentActivityRuleStoreRel>

    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @ResultMap("CtAgentActivityRuleStoreRelResult")
    fun selectOne(selectStatement: SelectStatementProvider): CtAgentActivityRuleStoreRel?

    /**
     * 查询代理活动规则与店铺关系
     *
     * @param id 代理活动规则与店铺关系主键
     * @return 代理活动规则与店铺关系
     */
    fun selectCtAgentActivityRuleStoreRelById(id: String): CtAgentActivityRuleStoreRel?

    /**
     * 查询代理活动规则与店铺关系列表
     *
     * @param ctAgentActivityRuleStoreRel 代理活动规则与店铺关系
     * @return 代理活动规则与店铺关系集合
     */
    fun selectCtAgentActivityRuleStoreRelList(ctAgentActivityRuleStoreRel: CtAgentActivityRuleStoreRel): List<CtAgentActivityRuleStoreRel>

    /**
     * 新增代理活动规则与店铺关系
     *
     * @param ctAgentActivityRuleStoreRel 代理活动规则与店铺关系
     * @return 结果
     */
    fun insertCtAgentActivityRuleStoreRel(ctAgentActivityRuleStoreRel: CtAgentActivityRuleStoreRel): Int

    /**
     * 修改代理活动规则与店铺关系
     *
     * @param ctAgentActivityRuleStoreRel 代理活动规则与店铺关系
     * @return 结果
     */
    fun updateCtAgentActivityRuleStoreRel(ctAgentActivityRuleStoreRel: CtAgentActivityRuleStoreRel): Int

    /**
     * 删除代理活动规则与店铺关系
     *
     * @param id 代理活动规则与店铺关系主键
     * @return 结果
     */
    fun deleteCtAgentActivityRuleStoreRelById(id: String): Int

    /**
     * 批量删除代理活动规则与店铺关系
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    fun deleteCtAgentActivityRuleStoreRelByIds(ids: Array<String>): Int
}
