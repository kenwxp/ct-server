package com.cloudtimes.account.service

import com.cloudtimes.account.domain.CtAgentDividend
import com.cloudtimes.account.dto.request.UpdateSubAgentDividendRequest

/**
 * 分润配置Service接口
 *
 * @author 沈兵
 * @date 2023-02-03
 */
interface ICtAgentDividendService {
    fun selectManyByUserId(userId: String) : List<CtAgentDividend>

    fun updateSubAgentDividend(request: UpdateSubAgentDividendRequest): Int

    /**
     * 查询分润配置
     *
     * @param id 分润配置主键
     * @return 分润配置
     */
    fun selectCtAgentDividendById(id: String): CtAgentDividend?

    /**
     * 查询分润配置列表
     *
     * @param ctAgentDividend 分润配置
     * @return 分润配置集合
     */
    fun selectCtAgentDividendList(ctAgentDividend: CtAgentDividend): List<CtAgentDividend>?

    /**
     * 新增分润配置
     *
     * @param ctAgentDividend 分润配置
     * @return 结果
     */
    fun insertCtAgentDividend(ctAgentDividend: CtAgentDividend): Int

    /**
     * 修改分润配置
     *
     * @param ctAgentDividend 分润配置
     * @return 结果
     */
    fun updateCtAgentDividend(ctAgentDividend: CtAgentDividend): Int

    /**
     * 批量删除分润配置
     *
     * @param ids 需要删除的分润配置主键集合
     * @return 结果
     */
    fun deleteCtAgentDividendByIds(ids: Array<String>): Int

    /**
     * 删除分润配置信息
     *
     * @param id 分润配置主键
     * @return 结果
     */
    fun deleteCtAgentDividendById(id: String): Int
}