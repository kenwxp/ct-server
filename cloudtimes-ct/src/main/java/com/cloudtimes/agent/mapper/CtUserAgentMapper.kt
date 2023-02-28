package com.cloudtimes.agent.mapper

import com.cloudtimes.account.dto.response.StoreAndCommission
import com.cloudtimes.agent.domain.CtUserAgent
import com.cloudtimes.agent.dto.response.AgentStoreOnlineStats
import com.cloudtimes.account.dto.response.TeamMember
import com.cloudtimes.agent.dto.response.AgentAssets

import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper
import org.mybatis.dynamic.sql.util.SqlProviderAdapter

/**
 * 代理Mapper接口
 *
 * @author 沈兵
 * @date 2023-02-07
 */
@Mapper
interface CtUserAgentMapper : CommonUpdateMapper  {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="CtUserAgentResult", value = [
        Result(column="user_id", property="userId", jdbcType=JdbcType.OTHER, id=true),
        Result(column="nick_name", property="nickName", jdbcType=JdbcType.VARCHAR),
        Result(column="agent_type", property="agentType", jdbcType=JdbcType.CHAR),
        Result(column="parent_user_id", property="parentUserId", jdbcType=JdbcType.OTHER),
        Result(column="cash_amount", property="cashAmount", jdbcType=JdbcType.DECIMAL),
        Result(column="total_withdrawal", property="totalWithdrawal", jdbcType=JdbcType.DECIMAL),
        Result(column="total_sales_reward", property="totalSalesReward", jdbcType=JdbcType.DECIMAL),
        Result(column="total_activity_reward", property="totalActivityReward", jdbcType=JdbcType.DECIMAL),
        Result(column="total_dividend", property="totalDividend", jdbcType=JdbcType.DECIMAL),
        Result(column="total_tributes", property="totalTributes", jdbcType=JdbcType.DECIMAL),
        Result(column="total_tributes_dividend", property="totalTributesDividend", jdbcType=JdbcType.DECIMAL),
        Result(column="total_tributes_commission", property="totalTributesCommission", jdbcType=JdbcType.DECIMAL),
        Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        Result(column="create_date", property="createDate", jdbcType=JdbcType.DATE),
        Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        Result(column="del_flag", property="delFlag", jdbcType=JdbcType.CHAR),
        Result(column="agent_area", property="agentArea", jdbcType=JdbcType.LONGVARCHAR)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<CtUserAgent>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("CtUserAgentResult")
    fun selectOne(selectStatement: SelectStatementProvider): CtUserAgent?

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    fun selectAgentAssets(selectStatement: SelectStatementProvider): AgentAssets?

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    fun selectTeamMember(selectStatement: SelectStatementProvider): TeamMember?

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    fun selectTeamMembers(selectStatement: SelectStatementProvider): List<TeamMember>

    /**
     * 查询代理
     *
     * @param userId 代理主键
     * @return 代理
     */
    fun selectCtUserAgentByUserId(userId: String): CtUserAgent?

    /**
     * 查询代理列表
     *
     * @param ctUserAgent 代理
     * @return 代理集合
     */
    fun selectCtUserAgentList(ctUserAgent: CtUserAgent): List<CtUserAgent>

    /** 查询代理门店列表 */
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    fun selectCtAgentShopList(selectStatement: SelectStatementProvider): List<StoreAndCommission>

    /** 查询代理门店详情 */
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    fun selectCtAgentShopDetail(selectStatement: SelectStatementProvider): StoreAndCommission

    /**
     * 查询代理门店上线统计
     *
     * @return 代理集合
     */
    @Select("""
        select store.build_state as build_state, count(*) as count
        from ct_store store
        where store.agent_id in (
            select user_id
            from  ct_user_agent 
            where user_id = #{userId}
            or parent_user_id = #{userId}
        )
        group by store.build_state
    """)
    fun selectCtAgentShopStats(@Param("userId") userId: String): List<AgentStoreOnlineStats>

    /**
     * 新增代理
     *
     * @param ctUserAgent 代理
     * @return 结果
     */
    fun insertCtUserAgent(ctUserAgent: CtUserAgent): Int

    /**
     * 修改代理
     *
     * @param ctUserAgent 代理
     * @return 结果
     */
    fun updateCtUserAgent(ctUserAgent: CtUserAgent): Int

    /**
     * 提现
     *
     * @param ctUserAgent 代理
     * @return 结果
     */
    fun cashWithdrawCtUserAgent(ctUserAgent: CtUserAgent): Int

    /**
     * 删除代理
     *
     * @param userId 代理主键
     * @return 结果
     */
    fun deleteCtUserAgentByUserId(userId: String): Int
}
