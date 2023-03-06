package com.cloudtimes.agent.mapper

import com.cloudtimes.agent.domain.CtAgentDividendSettlement
import com.cloudtimes.agent.dto.response.CtAgentDividendSettlementDto

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper

/**
 * 分润结算审核Mapper接口
 *
 * @author 沈兵
 * @date 2023-02-03
 */
@Mapper
interface CtAgentDividendSettlementMapper : CommonCountMapper, CommonDeleteMapper,
    CommonInsertMapper<CtAgentDividendSettlement>, CommonUpdateMapper {
    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @Results(
        id = "CtAgentDividendSettlementResult", value = [
            Result(column = "id", property = "id", jdbcType = JdbcType.OTHER, id = true),
            Result(column = "store_id", property = "storeId", jdbcType = JdbcType.OTHER),
            Result(column = "user_id", property = "userId", jdbcType = JdbcType.OTHER),
            Result(column = "parent_user_id", property = "parentUserId", jdbcType = JdbcType.OTHER),
            Result(column = "dividend_year_month", property = "dividendYearMonth", jdbcType = JdbcType.INTEGER),
            Result(column = "sales_total", property = "salesTotal", jdbcType = JdbcType.DECIMAL),
            Result(column = "dividend_ratio", property = "dividendRatio", jdbcType = JdbcType.DECIMAL),
            Result(column = "parent_dividend_ratio", property = "parentDividendRatio", jdbcType = JdbcType.DECIMAL),
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
            Result(column = "verify_state", property = "verifyState", jdbcType = JdbcType.CHAR),
            Result(column = "state", property = "state", jdbcType = JdbcType.CHAR),
            Result(column = "del_flag", property = "delFlag", jdbcType = JdbcType.CHAR),
            Result(column = "platform_approved_time", property = "platformApprovedTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "agent_approved_time", property = "agentApprovedTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "create_date", property = "createDate", jdbcType = JdbcType.DATE),
            Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
        ]
    )
    fun selectMany(selectStatement: SelectStatementProvider): List<CtAgentDividendSettlement>

    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @ResultMap("CtAgentDividendSettlementResult")
    fun selectOne(selectStatement: SelectStatementProvider): CtAgentDividendSettlement?

    /**
     * 查询分润结算审核
     *
     * @param id 分润结算审核主键
     * @return 分润结算审核
     */
    fun selectCtAgentDividendSettlementById(id: String): CtAgentDividendSettlement?

    /**
     * 查询分润结算审核列表
     *
     * @param ctAgentDividendSettlement 分润结算审核
     * @return 分润结算审核集合
     */
    fun selectCtAgentDividendSettlementList(ctAgentDividendSettlement: CtAgentDividendSettlement): List<CtAgentDividendSettlement>

    fun selectCtAgentDividendSettlementListPlus(ctAgentDividendSettlement: CtAgentDividendSettlement): List<CtAgentDividendSettlementDto>

    /**
     * 新增分润结算审核
     *
     * @param ctAgentDividendSettlement 分润结算审核
     * @return 结果
     */
    fun insertCtAgentDividendSettlement(ctAgentDividendSettlement: CtAgentDividendSettlement): Int

    /**
     * 修改分润结算审核
     *
     * @param ctAgentDividendSettlement 分润结算审核
     * @return 结果
     */
    fun updateCtAgentDividendSettlement(ctAgentDividendSettlement: CtAgentDividendSettlement): Int
}
