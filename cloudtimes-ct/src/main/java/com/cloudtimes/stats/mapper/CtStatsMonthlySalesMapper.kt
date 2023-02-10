package com.cloudtimes.stats.mapper

import com.cloudtimes.stats.domain.CtStatsMonthlySales
import com.cloudtimes.stats.dto.response.CommissionAndDividend
import org.apache.ibatis.annotations.Select

/**
 * 门店月销售统计Mapper接口
 *
 * @author 沈兵
 * @date 2023-02-09
 */
interface CtStatsMonthlySalesMapper {
    /**
     * 查询门店月销售统计列表
     *
     * @param ctStatsMonthlySales 门店月销售统计
     * @return 门店月销售统计集合
     */
    fun selectCtStatsMonthlySalesList(ctStatsMonthlySales: CtStatsMonthlySales): List<CtStatsMonthlySales>

    /**
     * 新增门店月销售统计
     *
     * @param ctStatsMonthlySales 门店月销售统计
     * @return 结果
     */
    fun insertCtStatsMonthlySales(ctStatsMonthlySales: CtStatsMonthlySales): Int

    @Select("""
        select 
        (
            -- 已结算佣金
            select coalesce(total_sales_reward, 0.00)
            from ct_user_agent
            where user_id = #{userId}
        ) as history_commission,
        (
            -- 已结算分润
            select coalesce(total_dividend+total_tributes, 0.00)
            from ct_user_agent
            where user_id = #{userId}
        ) as history_dividend,
        (
            -- 待结算分润
            select coalesce(sum(settle_amount), 0.00)
            from ct_agent_dividend_settlement
            where state = '0'
            and user_id = #{userId}
        )  as future_dividend
    """)
    fun selectCtStatsCommissionAndDividend(userId: String): CommissionAndDividend
}
