package com.cloudtimes.product.service.impl

import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import com.cloudtimes.product.domain.CtShopProduct
import com.cloudtimes.product.mapper.CtShopProductMapper
import com.cloudtimes.product.service.ICtShopProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 店铺商品Service业务层处理
 *
 * @author tank
 * @date 2023-02-15
 */
@DataSource(DataSourceType.CT)
@Service
class CtShopProductServiceImpl : ICtShopProductService {
    @Autowired
    private lateinit var ctShopProductMapper: CtShopProductMapper

    /**
     * 查询店铺商品
     *
     * @param id 店铺商品主键
     * @return 店铺商品
     */
    override fun selectCtShopProductById(id: String): CtShopProduct? {
        return ctShopProductMapper.selectCtShopProductById(id)
    }

    /**
     * 查询店铺商品列表
     *
     * @param ctShopProduct 店铺商品
     * @return 店铺商品
     */
    override fun selectCtShopProductList(ctShopProduct: CtShopProduct): List<CtShopProduct> {
        return ctShopProductMapper.selectCtShopProductList(ctShopProduct)
    }

    /**
     * 新增店铺商品
     *
     * @param ctShopProduct 店铺商品
     * @return 结果
     */
    override fun insertCtShopProduct(ctShopProduct: CtShopProduct): Int {
        ctShopProduct.createTime = DateUtils.getNowDate()
        return ctShopProductMapper.insertCtShopProduct(ctShopProduct)
    }

    /**
     * 修改店铺商品
     *
     * @param ctShopProduct 店铺商品
     * @return 结果
     */
    override fun updateCtShopProduct(ctShopProduct: CtShopProduct): Int {
        ctShopProduct.updateTime = DateUtils.getNowDate()
        return ctShopProductMapper.updateCtShopProduct(ctShopProduct)
    }

    /**
     * 批量删除店铺商品
     *
     * @param ids 需要删除的店铺商品主键
     * @return 结果
     */
    override fun deleteCtShopProductByIds(ids: Array<String>): Int {
        return ctShopProductMapper.deleteCtShopProductByIds(ids)
    }

    /**
     * 删除店铺商品信息
     *
     * @param id 店铺商品主键
     * @return 结果
     */
    override fun deleteCtShopProductById(id: String): Int {
        return ctShopProductMapper.deleteCtShopProductById(id)
    }
}