package com.cloudtimes.account.service.impl

import com.cloudtimes.account.domain.CtStoreDiscount
import com.cloudtimes.account.mapper.CtStoreDiscountMapper
import com.cloudtimes.account.service.ICtStoreDiscountService
import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 店铺会员折扣Service业务层处理
 *
 * @author 沈兵
 * @date 2023-03-08
 */
@DataSource(DataSourceType.CT)
@Service
class CtStoreDiscountServiceImpl : ICtStoreDiscountService {
    @Autowired
    private lateinit var ctStoreDiscountMapper: CtStoreDiscountMapper

    /**
     * 查询店铺会员折扣
     *
     * @param storeId 店铺会员折扣主键
     * @return 店铺会员折扣
     */
    override fun selectCtStoreDiscountByStoreId(storeId: String): CtStoreDiscount? {
        return ctStoreDiscountMapper.selectCtStoreDiscountByStoreId(storeId)
    }

    /**
     * 查询店铺会员折扣列表
     *
     * @param ctStoreDiscount 店铺会员折扣
     * @return 店铺会员折扣
     */
    override fun selectCtStoreDiscountList(ctStoreDiscount: CtStoreDiscount): List<CtStoreDiscount> {
        return ctStoreDiscountMapper.selectCtStoreDiscountList(ctStoreDiscount)
    }

    /**
     * 新增店铺会员折扣
     *
     * @param ctStoreDiscount 店铺会员折扣
     * @return 结果
     */
    override fun insertCtStoreDiscount(ctStoreDiscount: CtStoreDiscount): Int {
        ctStoreDiscount.createTime = DateUtils.getNowDate()
        return ctStoreDiscountMapper.insertCtStoreDiscount(ctStoreDiscount)
    }

    /**
     * 修改店铺会员折扣
     *
     * @param ctStoreDiscount 店铺会员折扣
     * @return 结果
     */
    override fun updateCtStoreDiscount(ctStoreDiscount: CtStoreDiscount): Int {
        ctStoreDiscount.updateTime = DateUtils.getNowDate()
        return ctStoreDiscountMapper.updateCtStoreDiscount(ctStoreDiscount)
    }

    /**
     * 批量删除店铺会员折扣
     *
     * @param storeIds 需要删除的店铺会员折扣主键
     * @return 结果
     */
    override fun deleteCtStoreDiscountByStoreIds(storeIds: Array<String>): Int {
        return ctStoreDiscountMapper.deleteCtStoreDiscountByStoreIds(storeIds)
    }

    /**
     * 删除店铺会员折扣信息
     *
     * @param storeId 店铺会员折扣主键
     * @return 结果
     */
    override fun deleteCtStoreDiscountByStoreId(storeId: String): Int {
        return ctStoreDiscountMapper.deleteCtStoreDiscountByStoreId(storeId)
    }
}