package com.cloudtimes.resources.service.impl

import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.resources.domain.CtRegion
import com.cloudtimes.resources.dto.response.CtRegionResponse
import com.cloudtimes.resources.mapper.CtRegionMapper
import com.cloudtimes.resources.mapper.provider.CtRegionProvider
import com.cloudtimes.resources.service.ICtRegionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 地区信息Service业务层处理
 *
 * @author tank
 * @date 2023-01-17
 */
@DataSource(DataSourceType.CT)
@Service
class CtRegionServiceImpl : ICtRegionService {
    @Autowired
    private lateinit var ctRegionMapper: CtRegionMapper

    /** 查询地区树 */
    override fun selectCtRegionTree(): List<CtRegionResponse> {
        fun helper(currentList: List<CtRegionResponse>, allList: List<CtRegionResponse>) {
            if (currentList.isEmpty()) {
                return
            }

            for (item in currentList) {
                val children = allList.filter { it.parentRegionCode == item.regionCode }
                if (children.isNotEmpty()) {
                    item.children = children
                    helper(children, allList)
                }
            }
        }

        val regionList = ctRegionMapper.selectMany(CtRegionProvider.selectAllRegions())
            .map { it.toRegionResponse() }
        val level1Regions = regionList.filter { it.regionLevel == "1" }
        helper(level1Regions, regionList)
        return level1Regions
    }

    /**
     * 查询地区信息
     *
     * @param regionCode 地区信息主键
     * @return 地区信息
     */
    override fun selectCtRegionByCode(regionCode: String): CtRegion? {
        return ctRegionMapper.selectCtRegionByCode(regionCode)
    }

    /**
     * 查询地区信息列表
     *
     * @param ctRegion 地区信息
     * @return 地区信息
     */
    override fun selectCtRegionList(ctRegion: CtRegion): List<CtRegion> {
        return ctRegionMapper.selectCtRegionList(ctRegion)
    }

    /**
     * 获取地区名
     *
     * @param regionCode 地区号
     * @return 地区名
     */
    override fun getRegionName(regionCode: String): String? {
        val selectCtRegionByCode = ctRegionMapper.selectCtRegionByCode(regionCode)
        if (selectCtRegionByCode != null) {
            return selectCtRegionByCode.regionName
        }
        return null
    }

}