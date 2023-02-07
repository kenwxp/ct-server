package com.cloudtimes.account.mapper

import com.cloudtimes.account.domain.CtUserAgent
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

/**
 * 代理Mapper接口
 *
 * @author 沈兵
 * @date 2023-02-07
 */
interface CtUserAgentMapper {
    /**
     * 查询代理
     *
     * @param userId 代理主键
     * @return 代理
     */
    fun selectCtUserAgentByUserId(userId: String): CtUserAgent?

    /**
     * 查询代理列表
     *
     * @param ctUserAgent 代理
     * @return 代理集合
     */
    fun selectCtUserAgentList(ctUserAgent: CtUserAgent): List<CtUserAgent>

    /**
     * 新增代理
     *
     * @param ctUserAgent 代理
     * @return 结果
     */
    fun insertCtUserAgent(ctUserAgent: CtUserAgent): Int

    /**
     * 修改代理
     *
     * @param ctUserAgent 代理
     * @return 结果
     */
    fun updateCtUserAgent(ctUserAgent: CtUserAgent): Int

    /**
     * 提现
     *
     * @param ctUserAgent 代理
     * @return 结果
     */
    fun cashWithdrawCtUserAgent(ctUserAgent: CtUserAgent): Int

    /**
     * 删除代理
     *
     * @param userId 代理主键
     * @return 结果
     */
    fun deleteCtUserAgentByUserId(userId: String): Int

    /**
     * 批量删除代理
     *
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    fun deleteCtUserAgentByUserIds(userIds: Array<String>): Int
}