package com.cloudtimes.product.service.impl

import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import com.cloudtimes.product.domain.CtShopPurchase
import com.cloudtimes.product.mapper.CtShopPurchaseMapper
import com.cloudtimes.product.service.ICtShopPurchaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 店铺商品采购Service业务层处理
 *
 * @author 沈兵
 * @date 2023-03-05
 */
@DataSource(DataSourceType.CT)
@Service
class CtShopPurchaseServiceImpl : ICtShopPurchaseService {
    @Autowired
    private lateinit var ctShopPurchaseMapper: CtShopPurchaseMapper

    /**
     * 查询店铺商品采购
     *
     * @param id 店铺商品采购主键
     * @return 店铺商品采购
     */
    override fun selectCtShopPurchaseById(id: String): CtShopPurchase? {
        return ctShopPurchaseMapper.selectCtShopPurchaseById(id)
    }

    /**
     * 查询店铺商品采购列表
     *
     * @param ctShopPurchase 店铺商品采购
     * @return 店铺商品采购
     */
    override fun selectCtShopPurchaseList(ctShopPurchase: CtShopPurchase): List<CtShopPurchase> {
        return ctShopPurchaseMapper.selectCtShopPurchaseList(ctShopPurchase)
    }

    /**
     * 新增店铺商品采购
     *
     * @param ctShopPurchase 店铺商品采购
     * @return 结果
     */
    override fun insertCtShopPurchase(ctShopPurchase: CtShopPurchase): Int {
        ctShopPurchase.createTime = DateUtils.getNowDate()
        return ctShopPurchaseMapper.insertCtShopPurchase(ctShopPurchase)
    }

    /**
     * 修改店铺商品采购
     *
     * @param ctShopPurchase 店铺商品采购
     * @return 结果
     */
    override fun updateCtShopPurchase(ctShopPurchase: CtShopPurchase): Int {
        ctShopPurchase.updateTime = DateUtils.getNowDate()
        return ctShopPurchaseMapper.updateCtShopPurchase(ctShopPurchase)
    }

    /**
     * 批量删除店铺商品采购
     *
     * @param ids 需要删除的店铺商品采购主键
     * @return 结果
     */
    override fun deleteCtShopPurchaseByIds(ids: Array<String>): Int {
        return ctShopPurchaseMapper.deleteCtShopPurchaseByIds(ids)
    }

    /**
     * 删除店铺商品采购信息
     *
     * @param id 店铺商品采购主键
     * @return 结果
     */
    override fun deleteCtShopPurchaseById(id: String): Int {
        return ctShopPurchaseMapper.deleteCtShopPurchaseById(id)
    }
}