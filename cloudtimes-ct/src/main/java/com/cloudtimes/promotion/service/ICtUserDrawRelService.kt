package com.cloudtimes.promotion.service

import com.cloudtimes.promotion.domain.CtUserDrawRel

/**
 * 用户中奖Service接口
 *
 * @author tank
 * @date 2023-03-08
 */
interface ICtUserDrawRelService {
    /**
     * 查询用户中奖
     *
     * @param userId 用户中奖主键
     * @return 用户中奖
     */
    fun selectCtUserDrawRelByUserId(userId: String): CtUserDrawRel?

    /**
     * 查询用户中奖列表
     *
     * @param ctUserDrawRel 用户中奖
     * @return 用户中奖集合
     */
    fun selectCtUserDrawRelList(ctUserDrawRel: CtUserDrawRel): List<CtUserDrawRel>

    /**
     * 新增用户中奖
     *
     * @param ctUserDrawRel 用户中奖
     * @return 结果
     */
    fun insertCtUserDrawRel(ctUserDrawRel: CtUserDrawRel): Int

    /**
     * 修改用户中奖
     *
     * @param ctUserDrawRel 用户中奖
     * @return 结果
     */
    fun updateCtUserDrawRel(ctUserDrawRel: CtUserDrawRel): Int

    /**
     * 批量删除用户中奖
     *
     * @param userIds 需要删除的用户中奖主键集合
     * @return 结果
     */
    fun deleteCtUserDrawRelByUserIds(userIds: Array<String>): Int

    /**
     * 删除用户中奖信息
     *
     * @param userId 用户中奖主键
     * @return 结果
     */
    fun deleteCtUserDrawRelByUserId(userId: String): Int
}