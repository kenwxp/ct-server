package com.cloudtimes.account.service

import com.cloudtimes.account.domain.CtStoreUser

/**
 * 门店会员Service接口
 *
 * @author 沈兵
 * @date 2023-03-08
 */
interface ICtStoreUserService {
    /**
     * 查询门店会员
     *
     * @param storeId 门店会员主键
     * @return 门店会员
     */
    fun selectCtStoreUserByStoreId(storeId: String): CtStoreUser?

    /**
     * 查询门店会员列表
     *
     * @param ctStoreUser 门店会员
     * @return 门店会员集合
     */
    fun selectCtStoreUserList(ctStoreUser: CtStoreUser): List<CtStoreUser>

    /**
     * 新增门店会员
     *
     * @param ctStoreUser 门店会员
     * @return 结果
     */
    fun insertCtStoreUser(ctStoreUser: CtStoreUser): Int

    /**
     * 修改门店会员
     *
     * @param ctStoreUser 门店会员
     * @return 结果
     */
    fun updateCtStoreUser(ctStoreUser: CtStoreUser): Int

    /**
     * 批量删除门店会员
     *
     * @param storeIds 需要删除的门店会员主键集合
     * @return 结果
     */
    fun deleteCtStoreUserByStoreIds(storeIds: Array<String>): Int

    /**
     * 删除门店会员信息
     *
     * @param storeId 门店会员主键
     * @return 结果
     */
    fun deleteCtStoreUserByStoreId(storeId: String): Int
}