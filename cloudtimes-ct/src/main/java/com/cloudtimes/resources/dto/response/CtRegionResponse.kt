package com.cloudtimes.resources.dto.response

import com.cloudtimes.common.annotation.Excel
import io.swagger.annotations.ApiModel
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle


@ApiModel(value = "CtRegionResponse", description = "地区")
class CtRegionResponse {
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

    /** 下级节点 */
    var children: List<CtRegionResponse>? = null

    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("regionCode", regionCode)
            .append("regionName", regionName)
            .append("regionShortName", regionShortName)
            .append("parentRegionCode", parentRegionCode)
            .append("regionLevel", regionLevel)
            .toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
