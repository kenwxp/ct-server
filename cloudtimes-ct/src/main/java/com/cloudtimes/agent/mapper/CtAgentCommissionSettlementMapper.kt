package com.cloudtimes.agent.mapper

import com.cloudtimes.agent.domain.CtAgentCommissionSettlement
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Update
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.util.SqlProviderAdapter
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper

/**
 * 销售佣金结算Mapper接口
 *
 * @author 沈兵
 * @date 2023-02-03
 */
@Mapper
interface CtAgentCommissionSettlementMapper : CommonCountMapper, CommonDeleteMapper,
    CommonInsertMapper<CtAgentCommissionSettlement>, CommonUpdateMapper {
    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @Results(
        id = "CtAgentCommissionSettlementResult", value = [
            Result(column = "id", property = "id", jdbcType = JdbcType.OTHER, id = true),
            Result(column = "store_id", property = "storeId", jdbcType = JdbcType.OTHER),
            Result(column = "user_id", property = "userId", jdbcType = JdbcType.OTHER),
            Result(column = "parent_user_id", property = "parentUserId", jdbcType = JdbcType.OTHER),
            Result(column = "sale_amount", property = "saleAmount", jdbcType = JdbcType.DECIMAL),
            Result(column = "cost_price", property = "costPrice", jdbcType = JdbcType.DECIMAL),
            Result(column = "parent_cost_price", property = "parentCostPrice", jdbcType = JdbcType.DECIMAL),
            Result(column = "tax_ratio", property = "taxRatio", jdbcType = JdbcType.DECIMAL),
            Result(column = "amount", property = "amount", jdbcType = JdbcType.DECIMAL),
            Result(column = "tax_amount", property = "taxAmount", jdbcType = JdbcType.DECIMAL),
            Result(column = "before_tax_amount", property = "beforeTaxAmount", jdbcType = JdbcType.DECIMAL),
            Result(column = "parent_amount", property = "parentAmount", jdbcType = JdbcType.DECIMAL),
            Result(column = "parent_tax_amount", property = "parentTaxAmount", jdbcType = JdbcType.DECIMAL),
            Result(
                column = "parent_before_tax_amount",
                property = "parentBeforeTaxAmount",
                jdbcType = JdbcType.DECIMAL
            ),
            Result(column = "is_store_online", property = "isStoreOnline", jdbcType = JdbcType.CHAR),
            Result(column = "verify_state", property = "verifyState", jdbcType = JdbcType.CHAR),
            Result(column = "state", property = "state", jdbcType = JdbcType.CHAR),
            Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
            Result(column = "del_flag", property = "delFlag", jdbcType = JdbcType.CHAR),
            Result(column = "platform_approved_time", property = "platformApprovedTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "agent_approved_time", property = "agentApprovedTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "create_date", property = "createDate", jdbcType = JdbcType.DATE),
            Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
        ]
    )
    fun selectMany(selectStatement: SelectStatementProvider): List<CtAgentCommissionSettlement>

    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @ResultMap("CtAgentCommissionSettlementResult")
    fun selectOne(selectStatement: SelectStatementProvider): CtAgentCommissionSettlement?

    /**
     * 查询销售佣金结算
     *
     * @param id 销售佣金结算主键
     * @return 销售佣金结算
     */
    fun selectCtAgentCommissionSettlementById(id: String): CtAgentCommissionSettlement?

    /**
     * 查询销售佣金结算列表
     *
     * @param ctAgentCommissionSettlement 销售佣金结算
     * @return 销售佣金结算集合
     */
    fun selectCtAgentCommissionSettlementList(ctAgentCommissionSettlement: CtAgentCommissionSettlement): List<CtAgentCommissionSettlement>

    /**
     * 新增销售佣金结算
     *
     * @param ctAgentCommissionSettlement 销售佣金结算
     * @return 结果
     */
    fun insertCtAgentCommissionSettlement(ctAgentCommissionSettlement: CtAgentCommissionSettlement): Int

    /**
     * 修改销售佣金结算
     *
     * @param ctAgentCommissionSettlement 销售佣金结算
     * @return 结果
     */
    fun updateCtAgentCommissionSettlement(ctAgentCommissionSettlement: CtAgentCommissionSettlement): Int

    /**
     * 代理佣金确认
     *
     * @param id 销售佣金结算主键
     * @return 结果
     */
    @Update(
        """
        UPDATE ct_agent_commission_settlement
        SET verify_state = '1',
            agent_approved_time = current_timestamp(),
            update_time = current_timestamp()
        WHERE id = #{id}
        """
    )
    fun agentConfirmCtAgentCommissionSettlementById(@Param("id") id: String): Int

    /**
     * 平台佣金确认
     *
     * @param id 销售佣金结算主键
     * @return 结果
     */
    @Update(
        """
        UPDATE ct_agent_commission_settlement
        SET verify_state = '2',
            platform_approved_time = current_timestamp(),
            update_time = current_timestamp()
        WHERE id = #{id}
        """
    )
    fun platformConfirmCtAgentCommissionSettlementById(@Param("id") id: String): Int

    /**
     * 删除销售佣金结算
     *
     * @param id 销售佣金结算主键
     * @return 结果
     */
    fun deleteCtAgentCommissionSettlementById(id: String): Int

    /**
     * 批量删除销售佣金结算
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    fun deleteCtAgentCommissionSettlementByIds(ids: Array<String>): Int
}
