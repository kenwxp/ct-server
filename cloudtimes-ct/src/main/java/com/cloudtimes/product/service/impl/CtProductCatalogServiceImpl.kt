package com.cloudtimes.product.service.impl

import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import com.cloudtimes.product.domain.CtProductCatalog
import com.cloudtimes.product.mapper.CtProductCatalogMapper
import com.cloudtimes.product.service.ICtProductCatalogService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 商品目录Service业务层处理
 *
 * @author tank
 * @date 2023-03-06
 */
@DataSource(DataSourceType.CT)
@Service
class CtProductCatalogServiceImpl : ICtProductCatalogService {
    @Autowired
    private lateinit var ctProductCatalogMapper: CtProductCatalogMapper

    /**
     * 查询商品目录
     *
     * @param id 商品目录主键
     * @return 商品目录
     */
    override fun selectCtProductCatalogByBarcode(barcode: String): CtProductCatalog? {
        return ctProductCatalogMapper.selectCtProductCatalogByBarcode(barcode)
    }

    /**
     * 查询商品目录列表
     *
     * @param ctProductCatalog 商品目录
     * @return 商品目录
     */
    override fun selectCtProductCatalogList(ctProductCatalog: CtProductCatalog): List<CtProductCatalog> {
        return ctProductCatalogMapper.selectCtProductCatalogList(ctProductCatalog)
    }

    /**
     * 新增商品目录
     *
     * @param ctProductCatalog 商品目录
     * @return 结果
     */
    override fun insertCtProductCatalog(ctProductCatalog: CtProductCatalog): Int {
        ctProductCatalog.createTime = DateUtils.getNowDate()
        return ctProductCatalogMapper.insertCtProductCatalog(ctProductCatalog)
    }

    /**
     * 修改商品目录
     *
     * @param ctProductCatalog 商品目录
     * @return 结果
     */
    override fun updateCtProductCatalog(ctProductCatalog: CtProductCatalog): Int {
        ctProductCatalog.updateTime = DateUtils.getNowDate()
        return ctProductCatalogMapper.updateCtProductCatalog(ctProductCatalog)
    }

    /**
     * 删除商品目录信息
     *
     * @param id 商品目录主键
     * @return 结果
     */
    override fun deleteCtProductCatalogByBarcode(barcode: String): Int {
        return ctProductCatalogMapper.deleteCtProductCatalogByBarcode(barcode)
    }
}