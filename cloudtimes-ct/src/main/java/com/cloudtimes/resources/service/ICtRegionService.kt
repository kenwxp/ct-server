package com.cloudtimes.resources.service

import com.cloudtimes.resources.domain.CtRegion

/**
 * 地区信息Service接口
 *
 * @author tank
 * @date 2023-01-17
 */
interface ICtRegionService {
    /** 查询地区树 */
    fun selectCtRegionTree(): List<CtRegion>

    /**
     * 查询地区信息
     *
     * @param id 地区信息主键
     * @return 地区信息
     */
    fun selectCtRegionById(id: String): CtRegion?

    /**
     * 查询地区信息列表
     *
     * @param ctRegion 地区信息
     * @return 地区信息集合
     */
    fun selectCtRegionList(ctRegion: CtRegion): List<CtRegion>
}