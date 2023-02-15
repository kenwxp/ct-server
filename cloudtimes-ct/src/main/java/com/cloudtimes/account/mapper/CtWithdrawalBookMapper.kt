package com.cloudtimes.account.mapper

import com.cloudtimes.account.domain.CtWithdrawalBook

/**
 * 提现登记薄Mapper接口
 *
 * @author 沈兵
 * @date 2023-02-03
 */
interface CtWithdrawalBookMapper {
    /**
     * 查询提现登记薄
     *
     * @param id 提现登记薄主键
     * @return 提现登记薄
     */
    fun selectCtWithdrawalBookById(id: String?): CtWithdrawalBook?

    /**
     * 查询提现登记薄列表
     *
     * @param ctWithdrawalBook 提现登记薄
     * @return 提现登记薄集合
     */
    fun selectCtWithdrawalBookList(ctWithdrawalBook: CtWithdrawalBook?): List<CtWithdrawalBook?>?

    /**
     * 新增提现登记薄
     *
     * @param ctWithdrawalBook 提现登记薄
     * @return 结果
     */
    fun insertCtWithdrawalBook(ctWithdrawalBook: CtWithdrawalBook?): Int

    /**
     * 修改提现登记薄
     *
     * @param ctWithdrawalBook 提现登记薄
     * @return 结果
     */
    fun updateCtWithdrawalBook(ctWithdrawalBook: CtWithdrawalBook?): Int

    /**
     * 删除提现登记薄
     *
     * @param id 提现登记薄主键
     * @return 结果
     */
    fun deleteCtWithdrawalBookById(id: String?): Int

    /**
     * 批量删除提现登记薄
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    fun deleteCtWithdrawalBookByIds(ids: Array<String?>?): Int
}