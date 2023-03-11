package com.cloudtimes.product.service.impl

import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import com.cloudtimes.product.domain.CtProductCategory
import com.cloudtimes.product.mapper.CtProductCategoryMapper
import com.cloudtimes.product.mapper.provider.CtProductCategoryProvider
import com.cloudtimes.product.service.ICtProductCategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 商品分类Service业务层处理
 *
 * @author 沈兵
 * @date 2023-03-05
 */
@DataSource(DataSourceType.CT)
@Service
class CtProductCategoryServiceImpl : ICtProductCategoryService {
    @Autowired
    private lateinit var ctProductCategoryMapper: CtProductCategoryMapper

    /**
     * 查询商品分类
     *
     * @param categoryCode 商品分类主键
     * @return 商品分类
     */
    override fun selectCtProductCategoryByCategoryCode(categoryCode: String): CtProductCategory? {
        return ctProductCategoryMapper.selectCtProductCategoryByCategoryCode(categoryCode)
    }

    /**
     * 查询商品分类列表
     *
     * @param ctProductCategory 商品分类
     * @return 商品分类
     */
    override fun selectCtProductCategoryList(ctProductCategory: CtProductCategory): List<CtProductCategory> {
        return ctProductCategoryMapper.selectMany(
            CtProductCategoryProvider.selectManyStmt(ctProductCategory)
        )
    }

    /**
     * 新增商品分类
     *
     * @param ctProductCategory 商品分类
     * @return 结果
     */
    override fun insertCtProductCategory(ctProductCategory: CtProductCategory): Int {
        ctProductCategory.createTime = DateUtils.getNowDate()
        return ctProductCategoryMapper.insertCtProductCategory(ctProductCategory)
    }

    /**
     * 修改商品分类
     *
     * @param ctProductCategory 商品分类
     * @return 结果
     */
    override fun updateCtProductCategory(ctProductCategory: CtProductCategory): Int {
        ctProductCategory.updateTime = DateUtils.getNowDate()
        return ctProductCategoryMapper.updateCtProductCategory(ctProductCategory)
    }

    /**
     * 批量删除商品分类
     *
     * @param categoryCodes 需要删除的商品分类主键
     * @return 结果
     */
    override fun deleteCtProductCategoryByCategoryCodes(categoryCodes: Array<String>): Int {
        return ctProductCategoryMapper.deleteCtProductCategoryByCategoryCodes(categoryCodes)
    }

    /**
     * 删除商品分类信息
     *
     * @param categoryCode 商品分类主键
     * @return 结果
     */
    override fun deleteCtProductCategoryByCategoryCode(categoryCode: String): Int {
        return ctProductCategoryMapper.deleteCtProductCategoryByCategoryCode(categoryCode)
    }
}