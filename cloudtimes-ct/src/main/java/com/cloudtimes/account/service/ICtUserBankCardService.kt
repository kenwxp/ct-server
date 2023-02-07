package com.cloudtimes.account.service

import com.cloudtimes.account.domain.CtUserBankCard

/**
 * 用户银行卡Service接口
 *
 * @author 沈兵
 * @date 2023-02-03
 */
interface ICtUserBankCardService {
    /**
     * 查询用户银行卡
     *
     * @param id 用户银行卡主键
     * @return 用户银行卡
     */
    fun selectCtUserBankCardById(id: String): CtUserBankCard?

    /**
     * 查询用户银行卡
     *
     * @param userId 用户主键
     * @return 用户银行卡
     */
    fun selectCtUserBankCardByUserId(userId: String): CtUserBankCard?

    /**
     * 查询用户银行卡列表
     *
     * @param ctUserBankCard 用户银行卡
     * @return 用户银行卡集合
     */
    fun selectCtUserBankCardList(ctUserBankCard: CtUserBankCard): List<CtUserBankCard>

    /**
     * 新增用户银行卡
     *
     * @param ctUserBankCard 用户银行卡
     * @return 结果
     */
    fun insertCtUserBankCard(ctUserBankCard: CtUserBankCard): Int

    /**
     * 修改用户银行卡
     *
     * @param ctUserBankCard 用户银行卡
     * @return 结果
     */
    fun updateCtUserBankCard(ctUserBankCard: CtUserBankCard): Int

    /**
     * 批量删除用户银行卡
     *
     * @param ids 需要删除的用户银行卡主键集合
     * @return 结果
     */
    fun deleteCtUserBankCardByIds(ids: Array<String>): Int

    /**
     * 删除用户银行卡信息
     *
     * @param id 用户银行卡主键
     * @return 结果
     */
    fun deleteCtUserBankCardById(id: String): Int
}