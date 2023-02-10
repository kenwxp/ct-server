package com.cloudtimes.stats.service

import com.cloudtimes.stats.domain.CtStatsMonthlySales
import com.cloudtimes.stats.dto.response.CommissionAndDividend

/**
 * 门店月销售统计Service接口
 *
 * @author 沈兵
 * @date 2023-02-09
 */
interface ICtAgentStatsService {
    /**
     * 查询门店月销售统计列表
     *
     * @param ctStatsMonthlySales 门店月销售统计
     * @return 门店月销售统计集合
     */
    fun selectCtStatsMonthlySalesList(ctStatsMonthlySales: CtStatsMonthlySales?): List<CtStatsMonthlySales?>?

    fun selectCtStatsCommisionAndDividend(userId: String): CommissionAndDividend

    /**
     * 新增门店月销售统计
     *
     * @param ctStatsMonthlySales 门店月销售统计
     * @return 结果
     */
    fun insertCtStatsMonthlySales(ctStatsMonthlySales: CtStatsMonthlySales?): Int
}