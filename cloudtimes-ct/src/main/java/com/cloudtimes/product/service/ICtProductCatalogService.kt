package com.cloudtimes.product.service

import com.cloudtimes.product.domain.CtProductCatalog

/**
 * 商品目录Service接口
 *
 * @author tank
 * @date 2023-03-06
 */
interface ICtProductCatalogService {
    /**
     * 查询商品目录
     *
     * @param id 商品目录主键
     * @return 商品目录
     */
    fun selectCtProductCatalogByBarcode(barcode: String): CtProductCatalog?

    /**
     * 查询商品目录列表
     *
     * @param ctProductCatalog 商品目录
     * @return 商品目录集合
     */
    fun selectCtProductCatalogList(ctProductCatalog: CtProductCatalog): List<CtProductCatalog>

    /**
     * 新增商品目录
     *
     * @param ctProductCatalog 商品目录
     * @return 结果
     */
    fun insertCtProductCatalog(ctProductCatalog: CtProductCatalog): Int

    /**
     * 修改商品目录
     *
     * @param ctProductCatalog 商品目录
     * @return 结果
     */
    fun updateCtProductCatalog(ctProductCatalog: CtProductCatalog): Int
}