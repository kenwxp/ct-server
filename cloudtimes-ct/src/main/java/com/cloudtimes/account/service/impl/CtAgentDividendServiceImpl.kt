package com.cloudtimes.account.service.impl

import com.cloudtimes.account.domain.CtAgentDividend
import com.cloudtimes.account.dto.request.UpdateSubAgentDividendRequest
import com.cloudtimes.account.mapper.CtAgentDividendMapper
import com.cloudtimes.account.mapper.CtUserAgentMapper
import com.cloudtimes.account.mapper.provider.CtAgentDividendProvider
import com.cloudtimes.account.mapper.provider.CtUserAgentProvider
import com.cloudtimes.account.service.ICtAgentDividendService
import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.AgentType
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.exception.ServiceException
import com.cloudtimes.common.utils.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 分润配置Service业务层处理
 *
 * @author 沈兵
 * @date 2023-02-03
 */
@DataSource(DataSourceType.CT)
@Service
class CtAgentDividendServiceImpl : ICtAgentDividendService {
    val logger: Logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private lateinit var agentMapper: CtUserAgentMapper

    @Autowired
    private lateinit var dividendMapper: CtAgentDividendMapper


    /** 根据代理ID查询分润配置 */
    override fun selectManyByUserId(userId: String): List<CtAgentDividend> {
        // 1. 查询代理
        val agent = agentMapper.selectOne(CtUserAgentProvider.selectById(userId)) ?:
            throw ServiceException("数据库异常，没有查询到代理信息")

        // 2. 查询出配置信息
        var dividendList = dividendMapper.selectMany(CtAgentDividendProvider.selectManyByUserId(userId))

        // 3. 普通代理要判断是否有配置信息，没有先初始化配置信息
        if ( agent.agentType == AgentType.GeneralAgent.code && dividendList.isEmpty()) {
            val parentAgent = agent.parentUserId ?: throw ServiceException("数控库异常，上级代理为空")

            // 3.1 查询出上级代理的配置
            val parentDividendList = dividendMapper.selectMany(
                CtAgentDividendProvider.selectManyByUserId(parentAgent)
            )

            // 3.2 根据上级代理的配置，初始化下级代理的配置
            for (dividend in parentDividendList) {
                dividendMapper.generalInsert(
                    CtAgentDividendProvider.insertOneWithParentConfig(dividend, agent.userId!!)
                )
            }

            // 3.3 重新查询下级代理的配置
            dividendList = dividendMapper.selectMany(CtAgentDividendProvider.selectManyByUserId(userId))
        }

        return dividendList
    }

    override fun updateSubAgentDividend(request: UpdateSubAgentDividendRequest): Int {
        // 1. 查询出上级代理的配置
        val parentDividendList = dividendMapper.selectMany(
            CtAgentDividendProvider.selectManyByUserId(request.userId!!)
        )
        if (parentDividendList.isEmpty()) {
            throw ServiceException("数控库异常，上级代理分润配置不存在")
        }

        // 2. 合法性检查，比较笔数，金额，比率是否合法
        if (request.dividendList!!.size != parentDividendList.size) {
            throw ServiceException("数控库异常，上下级代理分润配置档次不一致")
        }
        for (idx in request.dividendList!!.indices) {
            val subDividend = request.dividendList!![idx]
            val parentDividend = parentDividendList[idx]
            if (
                subDividend.parentUserId != request.userId ||
                subDividend.userId != request.subUserId ||
                subDividend.billAmount!!.compareTo(parentDividend.billAmount) != 0  ||
                subDividend.dividendRatio!! > parentDividend.dividendRatio!! ||
                subDividend.dividendRatio!! < BigDecimal.ZERO) {
                logger.error("request.userId = ${request.userId}")
                logger.error("request.subUserId = ${request.subUserId}")
                logger.error("subDividend = $subDividend")
                logger.error("parentDividend = $parentDividend")
                throw ServiceException("分润比率配置错误，请检查")
            }
        }

        // 3. 更新配置信息
        for (subDividend in request.dividendList!!) {
            dividendMapper.update(CtAgentDividendProvider.updateDividendRatio(subDividend))
        }

        return 1
    }

    /**
     * 查询分润配置
     *
     * @param id 分润配置主键
     * @return 分润配置
     */
    override fun selectCtAgentDividendById(id: String): CtAgentDividend? {
        return dividendMapper.selectCtAgentDividendById(id)
    }

    /**
     * 查询分润配置列表
     *
     * @param ctAgentDividend 分润配置
     * @return 分润配置
     */
    override fun selectCtAgentDividendList(ctAgentDividend: CtAgentDividend): List<CtAgentDividend> {
        return dividendMapper.selectCtAgentDividendList(ctAgentDividend)
    }

    /**
     * 新增分润配置
     *
     * @param ctAgentDividend 分润配置
     * @return 结果
     */
    override fun insertCtAgentDividend(ctAgentDividend: CtAgentDividend): Int {
        ctAgentDividend.createTime = DateUtils.getNowDate()
        return dividendMapper.insertCtAgentDividend(ctAgentDividend)
    }

    /**
     * 修改分润配置
     *
     * @param ctAgentDividend 分润配置
     * @return 结果
     */
    override fun updateCtAgentDividend(ctAgentDividend: CtAgentDividend): Int {
        ctAgentDividend.updateTime = DateUtils.getNowDate()
        return dividendMapper.updateCtAgentDividend(ctAgentDividend)
    }

    /**
     * 批量删除分润配置
     *
     * @param ids 需要删除的分润配置主键
     * @return 结果
     */
    override fun deleteCtAgentDividendByIds(ids: Array<String>): Int {
        return dividendMapper.deleteCtAgentDividendByIds(ids)
    }

    /**
     * 删除分润配置信息
     *
     * @param id 分润配置主键
     * @return 结果
     */
    override fun deleteCtAgentDividendById(id: String): Int {
        return dividendMapper.deleteCtAgentDividendById(id)
    }
}