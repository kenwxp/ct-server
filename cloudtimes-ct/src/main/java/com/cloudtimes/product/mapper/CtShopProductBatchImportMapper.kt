package com.cloudtimes.product.mapper

import com.cloudtimes.product.domain.CtShopProductBatchImport

/**
 * 店铺商品批量导入Mapper接口
 *
 * @author 沈兵
 * @date 2023-03-12
 */
interface CtShopProductBatchImportMapper {
    /**
     * 查询店铺商品批量导入
     *
     * @param fileName 店铺商品批量导入主键
     * @return 店铺商品批量导入
     */
    fun selectCtShopProductBatchImportByFileName(fileName: String): CtShopProductBatchImport?

    /**
     * 查询店铺商品批量导入列表
     *
     * @param ctShopProductBatchImport 店铺商品批量导入
     * @return 店铺商品批量导入集合
     */
    fun selectCtShopProductBatchImportList(ctShopProductBatchImport: CtShopProductBatchImport): List<CtShopProductBatchImport>

    /**
     * 新增店铺商品批量导入
     *
     * @param ctShopProductBatchImport 店铺商品批量导入
     * @return 结果
     */
    fun insertCtShopProductBatchImport(ctShopProductBatchImport: CtShopProductBatchImport): Int

    /**
     * 修改店铺商品批量导入
     *
     * @param ctShopProductBatchImport 店铺商品批量导入
     * @return 结果
     */
    fun updateCtShopProductBatchImport(ctShopProductBatchImport: CtShopProductBatchImport): Int
}