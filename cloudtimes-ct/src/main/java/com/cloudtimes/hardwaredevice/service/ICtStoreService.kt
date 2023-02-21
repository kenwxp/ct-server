package com.cloudtimes.hardwaredevice.service

import com.cloudtimes.hardwaredevice.domain.CtStore
import com.cloudtimes.hardwaredevice.dto.request.RegisterStoreRequest

/**
 * 门店Service接口
 *
 * @author tank
 * @date 2023-01-17
 */
interface ICtStoreService {
    /** 注册店铺 */
    fun registerStore(request: RegisterStoreRequest): CtStore?

    /**
     * 查询门店
     *
     * @param id 门店主键
     * @return 门店
     */
    fun selectCtStoreById(id: String): CtStore?

    /**
     * 查询门店列表
     *
     * @param ctStore 门店
     * @return 门店集合
     */
    fun selectCtStoreList(ctStore: CtStore): List<CtStore>

    /**
     * 新增门店
     *
     * @param ctStore 门店
     * @return 结果
     */
    fun insertCtStore(ctStore: CtStore): Int

    /**
     * 修改门店
     *
     * @param ctStore 门店
     * @return 结果
     */
    fun updateCtStore(ctStore: CtStore): Int

    /**
     * 批量删除门店
     *
     * @param ids 需要删除的门店主键集合
     * @return 结果
     */
    fun deleteCtStoreByIds(ids: Array<String>): Int

    /**
     * 删除门店信息
     *
     * @param id 门店主键
     * @return 结果
     */
    fun deleteCtStoreById(id: String): Int
}