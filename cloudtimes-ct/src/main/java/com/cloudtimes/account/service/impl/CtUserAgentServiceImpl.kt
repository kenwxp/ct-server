package com.cloudtimes.account.service.impl

import com.cloudtimes.account.domain.CtUserAgent
import com.cloudtimes.account.domain.CtUserAssetsBook
import com.cloudtimes.account.domain.CtWithdrawalBook
import com.cloudtimes.account.dto.request.AgentStoreRequest
import com.cloudtimes.account.dto.request.WithdrawCashRequest
import com.cloudtimes.account.dto.response.AgentShopStats
import com.cloudtimes.account.dto.response.StoreAndCommission
import com.cloudtimes.account.mapper.CtUserAgentMapper
import com.cloudtimes.account.mapper.CtUserAssetsBookMapper
import com.cloudtimes.account.mapper.CtWithdrawalBookMapper
import com.cloudtimes.account.service.ICtUserAgentService
import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.*
import com.cloudtimes.common.exception.ServiceException
import com.cloudtimes.common.utils.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Date

/**
 * 代理Service业务层处理
 *
 * @author 沈兵
 * @date 2023-02-07
 */
@DataSource(DataSourceType.CT)
@Service
class CtUserAgentServiceImpl : ICtUserAgentService {
    @Autowired
    private lateinit var ctUserAgentMapper: CtUserAgentMapper

    @Autowired
    private lateinit var ctUserAssetBookMapper: CtUserAssetsBookMapper

    @Autowired
    private lateinit var ctWithdrawalBookMapper: CtWithdrawalBookMapper

    /**
     * 查询代理
     *
     * @param userId 代理主键
     * @return 代理
     */
    override fun selectCtUserAgentByUserId(userId: String): CtUserAgent? {
        return ctUserAgentMapper.selectCtUserAgentByUserId(userId)
    }

    /**
     * 查询代理列表
     *
     * @param ctUserAgent 代理
     * @return 代理
     */
    override fun selectCtUserAgentList(ctUserAgent: CtUserAgent): List<CtUserAgent> {
        return ctUserAgentMapper.selectCtUserAgentList(ctUserAgent)
    }


    /** 查询代理门店列表 */
    override fun selectCtAgentShopList(agentStoreRequest: AgentStoreRequest): List<StoreAndCommission> {
        return ctUserAgentMapper.selectCtAgentShopList(agentStoreRequest)
    }


    /** 查询代理门店上线统计 */
    override fun selectCtAgentShopStats(userId: String): List<AgentShopStats> {
        val stats = ctUserAgentMapper.selectCtAgentShopStats(userId)

        val allStats = ShopBuildState.values().map {bs ->
            val found = stats.find { st -> st.buildState == bs.code }
            found ?: AgentShopStats(bs.code, 0)
        }

        return allStats
    }

    /**
     * 新增代理
     *
     * @param ctUserAgent 代理
     * @return 结果
     */
    override fun insertCtUserAgent(ctUserAgent: CtUserAgent): Int {
        ctUserAgent.createTime = DateUtils.getNowDate()
        return ctUserAgentMapper.insertCtUserAgent(ctUserAgent)
    }

    /**
     * 修改代理
     *
     * @param ctUserAgent 代理
     * @return 结果
     */
    override fun updateCtUserAgent(ctUserAgent: CtUserAgent): Int {
        ctUserAgent.updateTime = DateUtils.getNowDate()
        return ctUserAgentMapper.updateCtUserAgent(ctUserAgent)
    }

    /**
     * 批量删除代理
     *
     * @param userIds 需要删除的代理主键
     * @return 结果
     */
    override fun deleteCtUserAgentByUserIds(userIds: Array<String>): Int {
        return ctUserAgentMapper.deleteCtUserAgentByUserIds(userIds)
    }

    /**
     * 删除代理信息
     *
     * @param userId 代理主键
     * @return 结果
     */
    override fun deleteCtUserAgentByUserId(userId: String): Int {
        return ctUserAgentMapper.deleteCtUserAgentByUserId(userId)
    }

    /** 代理提现  */
    @Transactional
    override fun withdrawCache(withdrawRequest: WithdrawCashRequest) {
        val agentAssets = ctUserAgentMapper.selectCtUserAgentByUserId(withdrawRequest.userId!!)
            ?: throw ServiceException("未查询到用户资产信息");

        if ( agentAssets.cashAmount < withdrawRequest.amount ) {
            throw ServiceException("用户余额不足");
        }

        // Step 1. 登记资产变更
        val decreaseCurrentCashRecord = CtUserAssetsBook()
        with(decreaseCurrentCashRecord) {
            userId = withdrawRequest.userId
            bookType = AssetsBookType.AgentCashWithdraw.code
            assetsType = AssetsType.Cash.code
            amount = withdrawRequest.amount
            beforeAmount = agentAssets.cashAmount
            alterAmount = agentAssets.cashAmount.minus(withdrawRequest.amount!!)
            operateType = AssetsOperateType.Decrease.code
            remark = "提现扣减"
        }
        ctUserAssetBookMapper.insertCtUserAssetsBook(decreaseCurrentCashRecord)

        // Step 2. 登陆提现记录
        val withdrawRecord = CtWithdrawalBook(
            userId = withdrawRequest.userId,
            userType = UserType.Agent.code,
            amount = withdrawRequest.amount,
        )
        ctWithdrawalBookMapper.insertCtWithdrawalBook(withdrawRecord)

        // Step 3. 扣减现金，增加累计已提现
        agentAssets.cashAmount = agentAssets.cashAmount.minus(withdrawRequest.amount!!)
        agentAssets.totalWithdrawal = agentAssets.totalWithdrawal.add(withdrawRequest.amount)
        agentAssets.updateTime = Date()
        ctUserAgentMapper.cashWithdrawCtUserAgent(agentAssets)
    }
}