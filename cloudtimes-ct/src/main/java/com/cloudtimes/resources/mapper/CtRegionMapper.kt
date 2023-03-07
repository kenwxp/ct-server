package com.cloudtimes.resources.mapper

import com.cloudtimes.resources.domain.CtRegion

import org.apache.ibatis.type.JdbcType
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.SelectProvider
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper

/**
 * 地区信息Mapper接口
 *
 * @author tank
 * @date 2023-01-17
 */
@Mapper
interface CtRegionMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<CtRegion>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="CtRegionResult", value = [
        Result(column="id", property="id", jdbcType=JdbcType.OTHER, id=true),
        Result(column="region_name", property="regionName", jdbcType=JdbcType.VARCHAR),
        Result(column="region_short_name", property="regionShortName", jdbcType=JdbcType.VARCHAR),
        Result(column="region_code", property="regionCode", jdbcType=JdbcType.VARCHAR),
        Result(column="region_parent_id", property="regionParentId", jdbcType=JdbcType.OTHER),
        Result(column="region_level", property="regionLevel", jdbcType=JdbcType.INTEGER),
        Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        Result(column="del_flag", property="delFlag", jdbcType=JdbcType.CHAR)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<CtRegion>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("CtRegionResult")
    fun selectOne(selectStatement: SelectStatementProvider): CtRegion?

    /**
     * 查询地区信息
     *
     * @param id 地区信息主键
     * @return 地区信息
     */
    fun selectCtRegionById(id: String): CtRegion?

    /**
     * 查询地区信息
     *
     * @param regionCode 地区信息主键
     * @return 地区信息
     */
    fun selectCtRegionByCode(regionCode: String): CtRegion?

    /**
     * 查询地区信息列表
     *
     * @param ctRegion 地区信息
     * @return 地区信息集合
     */
    fun selectCtRegionList(ctRegion: CtRegion): List<CtRegion>

    /**
     * 新增地区信息
     *
     * @param ctRegion 地区信息
     * @return 结果
     */
    fun insertCtRegion(ctRegion: CtRegion): Int

    /**
     * 修改地区信息
     *
     * @param ctRegion 地区信息
     * @return 结果
     */
    fun updateCtRegion(ctRegion: CtRegion): Int

    /**
     * 删除地区信息
     *
     * @param id 地区信息主键
     * @return 结果
     */
    fun deleteCtRegionById(id: String): Int

    /**
     * 批量删除地区信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    fun deleteCtRegionByIds(ids: Array<String>): Int
}