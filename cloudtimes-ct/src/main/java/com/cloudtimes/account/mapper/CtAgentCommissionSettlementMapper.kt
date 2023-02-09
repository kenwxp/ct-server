package com.cloudtimes.account.mapper

import com.cloudtimes.account.domain.CtAgentCommissionSettlement
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Update

/**
 * 销售佣金结算Mapper接口
 *
 * @author 沈兵
 * @date 2023-02-03
 */
@Mapper
interface CtAgentCommissionSettlementMapper {
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
    @Update("""
        UPDATE ct_agent_commission_settlement
        SET is_agent_ok = '1',
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
    @Update("""
        UPDATE ct_agent_commission_settlement
        SET is_platform_ok = '1',
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