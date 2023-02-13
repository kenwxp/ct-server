package com.cloudtimes.account.service

import com.cloudtimes.account.domain.CtUserAssetsBook

/**
 * 用户资产簿记Service接口
 *
 * @author 沈兵
 * @date 2023-02-07
 */
interface ICtUserAssetsBookService {
    /**
     * 查询用户资产簿记
     *
     * @param id 用户资产簿记主键
     * @return 用户资产簿记
     */
    fun selectCtUserAssetsBookById(id: String): CtUserAssetsBook?

    /**
     * 查询用户资产簿记列表
     *
     * @param ctUserAssetsBook 用户资产簿记
     * @return 用户资产簿记集合
     */
    fun selectCtUserAssetsBookList(ctUserAssetsBook: CtUserAssetsBook): List<CtUserAssetsBook>
}