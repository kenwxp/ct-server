package com.cloudtimes.resources.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import io.swagger.annotations.ApiModel
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

/**
 * 地区信息对象 ct_region
 *
 * @author tank
 * @date 2023-01-17
 */
@ApiModel(value = "CtRegion", description = "地区")
class CtRegion : BaseEntity() {
    /** 序列号  */
    var id: String? = null

    /** 地区编码  */
    @Excel(name = "地区编码")
    var regionCode: String? = null

    /** 父地区编码  */
    @Excel(name = "父地区编码")
    var parentRegionCode: String? = null

    /** 地区名称  */
    @Excel(name = "地区名称")
    var regionName: String? = null

    /** 地区缩写  */
    @Excel(name = "地区缩写")
    var regionShortName: String? = null

    /** 地区级别  */
    @Excel(name = "地区级别")
    var regionLevel: String? = null

    /** 是否删除  */
    var delFlag: Long? = null

    /** 下级节点 */
    var children: List<CtRegion>? = null

    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", id)
            .append("regionName", regionName)
            .append("regionShortName", regionShortName)
            .append("regionCode", regionCode)
            .append("parentRegionCode", parentRegionCode)
            .append("regionLevel", regionLevel)
            .append("createTime", createTime)
            .append("updateTime", updateTime)
            .append("delFlag", delFlag)
            .toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}