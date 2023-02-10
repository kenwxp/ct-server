package com.cloudtimes.stats.service.impl

import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import com.cloudtimes.stats.domain.CtStatsMonthlySales
import com.cloudtimes.stats.dto.response.CommissionAndDividend
import com.cloudtimes.stats.mapper.CtStatsMonthlySalesMapper
import com.cloudtimes.stats.service.ICtAgentStatsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 代理数据统计Service业务层处理
 *
 * @author 沈兵
 * @date 2023-02-09
 */
@DataSource(DataSourceType.CT)
@Service
class CtAgentStatsServiceImpl : ICtAgentStatsService {
    @Autowired
    private lateinit var ctStatsMonthlySalesMapper: CtStatsMonthlySalesMapper

    /**
     * 查询门店月销售统计列表
     *
     * @param ctStatsMonthlySales 门店月销售统计
     * @return 门店月销售统计
     */
    override fun selectCtStatsMonthlySalesList(ctStatsMonthlySales: CtStatsMonthlySales?): List<CtStatsMonthlySales?>? {
        return ctStatsMonthlySalesMapper.selectCtStatsMonthlySalesList(ctStatsMonthlySales!!)
    }

    /**
     * 新增门店月销售统计
     *
     * @param ctStatsMonthlySales 门店月销售统计
     * @return 结果
     */
    override fun insertCtStatsMonthlySales(ctStatsMonthlySales: CtStatsMonthlySales?): Int {
        ctStatsMonthlySales!!.createTime = DateUtils.getNowDate()
        return ctStatsMonthlySalesMapper.insertCtStatsMonthlySales(ctStatsMonthlySales)
    }

    override fun selectCtStatsCommisionAndDividend(userId: String): CommissionAndDividend {
        // :TODO: 计算待结算佣金
        return ctStatsMonthlySalesMapper.selectCtStatsCommissionAndDividend(userId)
    }
}