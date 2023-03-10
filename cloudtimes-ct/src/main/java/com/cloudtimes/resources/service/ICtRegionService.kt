package com.cloudtimes.resources.service

import com.cloudtimes.resources.domain.CtRegion
import com.cloudtimes.resources.dto.response.CtRegionResponse

/**
 * 地区信息Service接口
 *
 * @author tank
 * @date 2023-01-17
 */
interface ICtRegionService {
    /** 查询地区树 */
    fun selectCtRegionTree(): List<CtRegionResponse>

    /**
     * 查询地区信息
     *
     * @param id 地区信息主键
     * @return 地区信息
     */
    fun selectCtRegionByCode(id: String): CtRegion?

    /**
     * 查询地区信息列表
     *
     * @param ctRegion 地区信息
     * @return 地区信息集合
     */
    fun selectCtRegionList(ctRegion: CtRegion): List<CtRegion>

    /**
     * 获取地区名
     *
     * @param regionCode 地区号
     * @return 地区名
     */
    fun getRegionName(regionCode: String): String?
}