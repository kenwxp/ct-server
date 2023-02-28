package com.cloudtimes.agent.service.impl

import com.cloudtimes.account.domain.CtTransferBook
import com.cloudtimes.agent.domain.CtUserAgent
import com.cloudtimes.account.domain.CtUserAssetsBook
import com.cloudtimes.account.domain.CtWithdrawalBook
import com.cloudtimes.agent.dto.request.AgentStoreListRequest
import com.cloudtimes.account.dto.request.TransferCashRequest
import com.cloudtimes.account.dto.request.WithdrawCashRequest
import com.cloudtimes.agent.dto.response.AgentStoreOnlineStats
import com.cloudtimes.account.dto.response.StoreAndCommission
import com.cloudtimes.account.dto.response.TeamMember
import com.cloudtimes.account.mapper.CtTransferBookMapper
import com.cloudtimes.agent.mapper.CtUserAgentMapper
import com.cloudtimes.account.mapper.CtUserAssetsBookMapper
import com.cloudtimes.account.mapper.CtWithdrawalBookMapper
import com.cloudtimes.account.mapper.provider.CtTransferBookProvider
import com.cloudtimes.agent.mapper.provider.CtUserAgentProvider
import com.cloudtimes.account.mapper.provider.CtUserAssetsBookProvider
import com.cloudtimes.agent.dto.request.AgentStoreDetailRequest
import com.cloudtimes.agent.dto.request.StoreProfitRequest
import com.cloudtimes.agent.dto.response.AgentAssets
import com.cloudtimes.agent.dto.response.AgentStoreProfitStats
import com.cloudtimes.agent.service.ICtUserAgentService
import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.*
import com.cloudtimes.common.exception.ServiceException
import com.cloudtimes.common.utils.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Date

// :TODO: 是否增加分布式锁?

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
    private lateinit var agentMapper: CtUserAgentMapper

    @Autowired
    private lateinit var assetBookMapper: CtUserAssetsBookMapper

    @Autowired
    private lateinit var withdrawalBookMapper: CtWithdrawalBookMapper

    @Autowired
    private lateinit var transferBookMapper: CtTransferBookMapper

    override fun selectTeamMember(userId: String): TeamMember? {
        return agentMapper.selectTeamMember(
            CtUserAgentProvider.selectTeamMember(userId)
        )
    }

    override fun selectTeamMembers(userId: String): List<TeamMember> {
        return agentMapper.selectTeamMembers(
            CtUserAgentProvider.selectTeamMembers(userId)
        )
    }

    /** 查询代理资产 */
    override fun selectAgentAssets(userId: String): AgentAssets? {
        val assets = agentMapper.selectAgentAssets(CtUserAgentProvider.selectById(userId)) ?: return null
        // :TODO: 返回待结算资产
        return assets
    }

    /**
     * 查询代理
     *
     * @param userId 代理主键
     * @return 代理
     */
    override fun selectCtUserAgentByUserId(userId: String): CtUserAgent? {
        return agentMapper.selectOne(CtUserAgentProvider.selectById(userId))
    }

    /**
     * 查询代理列表
     *
     * @param ctUserAgent 代理
     * @return 代理
     */
    override fun selectCtUserAgentList(ctUserAgent: CtUserAgent): List<CtUserAgent> {
        return agentMapper.selectCtUserAgentList(ctUserAgent)
    }

    /** 查询代理门店列表 */
    override fun selectCtAgentShopList(request: AgentStoreListRequest): List<StoreAndCommission> {
        val stores = agentMapper.selectCtAgentShopList(
            CtUserAgentProvider.selectAgentStoresStmt(request)
        )

        stores.forEach { st ->
            st.commissionState = st.commissionState ?: SettlementState.None.code
            st.verifyState = st.verifyState ?: VerifyState.None.code
        }

        return stores
    }

    /** 查询代理门店详情 */
    override fun selectCtAgentShopDetail(request: AgentStoreDetailRequest): StoreAndCommission? {
        val detail = agentMapper.selectCtAgentShopDetail(
            CtUserAgentProvider.selectAgentStoreStmt(request)
        )

        return detail
    }

    /** 查询代理门店上线统计 */
    override fun selectCtAgentShopOnlineStats(userId: String): List<AgentStoreOnlineStats> {
        val stats = agentMapper.selectCtAgentShopStats(userId)

        val allStats = ShopBuildState.values().map { bs ->
            val found = stats.find { st -> st.buildState == bs.code }
            found ?: AgentStoreOnlineStats(bs.code, 0)
        }

        return allStats
    }

    override fun selectCtAgentShopProfitStats(userId: StoreProfitRequest): AgentStoreProfitStats {
        TODO("Not yet implemented")
    }

    /**
     * 新增代理
     *
     * @param ctUserAgent 代理
     * @return 结果
     */
    override fun insertCtUserAgent(ctUserAgent: CtUserAgent): Int {
        ctUserAgent.createTime = DateUtils.getNowDate()
        return agentMapper.insertCtUserAgent(ctUserAgent)
    }

    /**
     * 修改代理
     *
     * @param ctUserAgent 代理
     * @return 结果
     */
    override fun updateCtUserAgent(ctUserAgent: CtUserAgent): Int {
        ctUserAgent.updateTime = DateUtils.getNowDate()
        return agentMapper.updateCtUserAgent(ctUserAgent)
    }

    /**
     * 删除代理信息
     *
     * @param userId 代理主键
     * @return 结果
     */
    override fun deleteCtUserAgentByUserId(userId: String): Int {
        return agentMapper.deleteCtUserAgentByUserId(userId)
    }

    /** 代理提现  */
    @Transactional
    override fun withdrawCash(request: WithdrawCashRequest) {
        val agentAssets = agentMapper.selectCtUserAgentByUserId(request.userId!!)
            ?: throw ServiceException("未查询到用户资产信息")

        if (agentAssets.cashAmount!! < request.amount!!) {
            throw ServiceException("用户余额不足")
        }

        // Step 1. 登记资产变更
        val decreaseCurrentCashRecord = CtUserAssetsBook()
        with(decreaseCurrentCashRecord) {
            yearMonth = DateUtils.getYearMonth()
            userId = request.userId
            bookType = AssetsBookType.AgentCashWithdraw.code
            assetsType = AssetsType.Cash.code
            amount = request.amount
            beforeTaxAmount = request.amount
            beforeAmount = agentAssets.cashAmount
            alterAmount = agentAssets.cashAmount!!.minus(request.amount)
            operateType = AssetsOperateType.Decrease.code
            remark = "提现扣减"
        }
        assetBookMapper.insertOne(CtUserAssetsBookProvider.insertOne(decreaseCurrentCashRecord))

        // Step 2. 登陆提现记录
        val withdrawRecord = CtWithdrawalBook(
            userId = request.userId,
            userType = UserType.Agent.code,
            amount = request.amount,
        )
        withdrawalBookMapper.insertCtWithdrawalBook(withdrawRecord)

        // Step 3. 扣减现金，增加累计已提现
        agentAssets.cashAmount = agentAssets.cashAmount!!.minus(request.amount)
        agentAssets.totalWithdrawal = agentAssets.totalWithdrawal!!.add(request.amount)
        agentAssets.updateTime = Date()
        agentMapper.cashWithdrawCtUserAgent(agentAssets)
    }


    /** 代理提现  */
    @Transactional
    override fun transferCash(transferCashRequest: TransferCashRequest) {
        val payer = selectCtUserAgentByUserId(transferCashRequest.payerUserId!!)
            ?: throw ServiceException("查询付款人失败")

        val payee = selectCtUserAgentByUserId(transferCashRequest.payeeUserId!!)
            ?: throw ServiceException("查询收款人失败")


        val payerId = payer.userId!!
        val payeeId = payee.userId!!

        if (payer.cashAmount!! < transferCashRequest.amount!!) {
            throw ServiceException("余额不足");
        }

        val transferAmount = transferCashRequest.amount


        // Step 1. 登记转账流水
        val transferBook = CtTransferBook().apply {
            this.payer = payerId
            this.payee = payeeId
            yearMonth = DateUtils.getYearMonth()
            amount = transferAmount
            remark = transferCashRequest.remark
        }
        transferBookMapper.insert(CtTransferBookProvider.insertOne(transferBook))

        // Step 2. 登记转账分录
        val withdrawCashRecord = CtUserAssetsBook()
        with(withdrawCashRecord) {
            transferId = transferBook.id
            yearMonth = DateUtils.getYearMonth()
            userId = payerId
            bookType = AssetsBookType.AgentTransfer.code
            assetsType = AssetsType.Cash.code
            amount = transferAmount
            beforeTaxAmount = transferAmount
            beforeAmount = payer.cashAmount
            alterAmount = payer.cashAmount!!.minus(transferAmount)
            operateType = AssetsOperateType.Decrease.code
            remark = "转账付款"
        }
        assetBookMapper.insertOne(CtUserAssetsBookProvider.insertOne(withdrawCashRecord))

        val depositCashRecord = CtUserAssetsBook()
        with(depositCashRecord) {
            transferId = transferBook.id
            yearMonth = DateUtils.getYearMonth()
            userId = payeeId
            bookType = AssetsBookType.AgentTransfer.code
            assetsType = AssetsType.Cash.code
            amount = transferAmount
            beforeTaxAmount = transferAmount
            beforeAmount = payee.cashAmount
            alterAmount = payee.cashAmount!!.plus(transferAmount)
            operateType = AssetsOperateType.Increase.code
            remark = "转账收款"
        }
        assetBookMapper.insertOne(CtUserAssetsBookProvider.insertOne(depositCashRecord))

        // Step 3. 更新用户资产
        payer.cashAmount = payer.cashAmount!!.minus(transferAmount)
        payer.updateTime = Date()
        agentMapper.update(CtUserAgentProvider.updateAgentCashStmt(payer))

        payee.cashAmount = payee.cashAmount!!.add(transferAmount)
        payee.updateTime = Date()
        agentMapper.update(CtUserAgentProvider.updateAgentCashStmt(payee))
    }
}
