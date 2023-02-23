package com.cloudtimes.account.service.impl

import com.cloudtimes.account.domain.CtUserAssetsBook
import com.cloudtimes.account.dto.request.QueryAssetsBookRequest
import com.cloudtimes.account.dto.response.QueryAssetsBookResponse
import com.cloudtimes.account.mapper.CtUserAssetsBookMapper
import com.cloudtimes.account.mapper.provider.CtUserAssetsBookProvider
import com.cloudtimes.account.service.ICtUserAssetsBookService
import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 用户资产簿记Service业务层处理
 *
 * @author 沈兵
 * @date 2023-02-07
 */
@DataSource(DataSourceType.CT)
@Service
class CtUserAssetsBookServiceImpl : ICtUserAssetsBookService {
    @Autowired
    private lateinit var assetsBookMapper: CtUserAssetsBookMapper

    /** 查询代理账单簿记 */
    override fun selectAgentAssetsBookList(request: QueryAssetsBookRequest): List<QueryAssetsBookResponse> {
        return assetsBookMapper.selectManyEnhanced(
            CtUserAssetsBookProvider.selectByUserStmt(request)
        )
    }

    /**
     * 查询用户资产簿记
     *
     * @param id 用户资产簿记主键
     * @return 用户资产簿记
     */
    override fun selectCtUserAssetsBookById(id: String): CtUserAssetsBook? {
        return assetsBookMapper.selectOne(CtUserAssetsBookProvider.selectByPrimaryKey(id))
    }

    /**
     * 查询用户资产簿记列表
     *
     * @param ctUserAssetsBook 用户资产簿记
     * @return 用户资产簿记
     */
    override fun selectCtUserAssetsBookList(ctUserAssetsBook: CtUserAssetsBook): List<CtUserAssetsBook> {
        return assetsBookMapper.selectMany(CtUserAssetsBookProvider.selectMany(ctUserAssetsBook))
    }
}