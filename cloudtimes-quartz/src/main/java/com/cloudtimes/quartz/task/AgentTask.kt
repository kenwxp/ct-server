package com.cloudtimes.quartz.task

import com.cloudtimes.account.mapper.CtAgentCommissionSettlementMapper
import com.cloudtimes.account.mapper.CtUserAgentMapper
import com.cloudtimes.account.mapper.provider.CtAgentCommissionSettlementProvider
import com.cloudtimes.account.mapper.provider.CtUserAgentProvider
import com.cloudtimes.hardwaredevice.mapper.CtStoreMapper
import com.cloudtimes.hardwaredevice.mapper.provider.CTStoreProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.LocalDate

@Component("agentTask")
class AgentTask {
    private var logger: Logger = LoggerFactory.getLogger(javaClass.simpleName)

    @Autowired
    lateinit var commissionSettlementMapper: CtAgentCommissionSettlementMapper

    @Autowired
    lateinit var storeMapper: CtStoreMapper

    @Autowired
    lateinit var agentMapper: CtUserAgentMapper

    /** 佣金结算准备 */
    @Transactional
    fun prepareCommissionSettlement() {
        // Step 1. 获取未上线的店铺
        val notOnlineSettlements = commissionSettlementMapper.selectMany(
            CtAgentCommissionSettlementProvider.selectNotOnlineStores()
        )
        if (notOnlineSettlements.isEmpty()) {
            logger.info("没有需要处理的佣金结算记录")
            return
        }

        // Step 2. 获取已经上线的店铺
        val notOnlineStoreIds = notOnlineSettlements.map { it.storeId!! }
        val onlineShops = storeMapper.selectMany(
            CTStoreProvider.selectOnlineStoresByIds(notOnlineStoreIds)
        )
        if (onlineShops.isEmpty()) {
            logger.info("没有店铺上线, 无须处理")
            return
        }

        // Step 3. 更新佣金结算表里的上线店铺
        val onlineStoreIds = onlineShops.map { it.id!! }
        val updatedRows = commissionSettlementMapper.update(
            CtAgentCommissionSettlementProvider.updateOnlineStores(onlineStoreIds)
        )
        logger.info("更新结算记录为: $updatedRows")
    }


    /** 分润试算, 次日5点计算上一日的分润 */
    @Transactional
    fun preCalculateDividend() {
        // Step 1. 查询店铺
        val stores = storeMapper.selectMany(
            CTStoreProvider.selectOnlineStoresWithAgent()
        )
        if (stores.isEmpty()) {
            logger.info("没有需要处理的店铺")
            return
        }

        // Step 2. 按店铺登记分润
        val now = LocalDate.now()
        val yesterday = now.minusDays(1)
        val yearMonth = yesterday.year * 100 + yesterday.monthValue

        for (store in stores) {
            val storeAgent = agentMapper.selectOne(
                CtUserAgentProvider.selectById(store.agentId!!)
            )

            if (storeAgent == null) {
                logger.error("店铺没有找到对应的代理")
                continue
            }

            // :TODO:
            val parentAgent = storeAgent.parentUserId?.let { parentUser ->
                CtUserAgentProvider.selectById(parentUser)
            }


        }
    }
}