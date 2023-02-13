package com.cloudtimes.account.mapper

import com.cloudtimes.account.domain.CtUserCards

/**
 * 卡劵维护Mapper接口
 *
 * @author 沈兵
 * @date 2023-02-03
 */
interface CtUserCardsMapper {
    /**
     * 查询卡劵维护
     *
     * @param id 卡劵维护主键
     * @return 卡劵维护
     */
    fun selectCtUserCardsById(id: String): CtUserCards?

    /**
     * 查询卡劵维护列表
     *
     * @param ctUserCards 卡劵维护
     * @return 卡劵维护集合
     */
    fun selectCtUserCardsList(ctUserCards: CtUserCards): List<CtUserCards>

    /**
     * 新增卡劵维护
     *
     * @param ctUserCards 卡劵维护
     * @return 结果
     */
    fun insertCtUserCards(ctUserCards: CtUserCards): Int

    /**
     * 修改卡劵维护
     *
     * @param ctUserCards 卡劵维护
     * @return 结果
     */
    fun updateCtUserCards(ctUserCards: CtUserCards): Int

    /**
     * 删除卡劵维护
     *
     * @param id 卡劵维护主键
     * @return 结果
     */
    fun deleteCtUserCardsById(id: String): Int

    /**
     * 批量删除卡劵维护
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    fun deleteCtUserCardsByIds(ids: Array<String>): Int
}