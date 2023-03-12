package com.cloudtimes.agent.dto.response

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.math.BigDecimal
import java.util.*

/**
 * 代理活动2规则对象 ct_agent_activity2_rule
 *
 * @author 沈兵
 * @date 2023-02-17
 */
@Schema(description = "代理活动2规则")
open class CtAgentActivity2RuleDto : BaseEntity() {
    @Schema(description = "编号")
    var id: String? = null

    @Schema(description = "活动编号")
    @Excel(name = "活动编号")
    var activityId: String? = null

    @Schema(description = "活动规则标题")
    @Excel(name = "活动规则标题")
    var title: String? = null

    @Schema(description = "活动规则内容")
    @Excel(name = "活动规则内容")
    var content: String? = null

    @Schema(description = "活动类型")
    @Excel(name = "活动类型")
    var activityType: String? = null

    @Schema(description = "活动地区码")
    @Excel(name = "活动地区码")
    var regionCode: String? = null

    @Schema(description = "活动地区名称")
    @Excel(name = "活动地区名称")
    var regionName: String? = null

    @Schema(description = "奖励门店数")
    @Excel(name = "奖励门店数")
    var storeCount: Long? = null

    @Schema(description = "已奖励门店数")
    @Excel(name = "已奖励门店数")
    var usedStoreCount: Long? = null

    @Schema(description = "手续费费率")
    @Excel(name = "手续费费率")
    var taxRatio: BigDecimal? = null

    @Schema(description = "操作管理员")
    @Excel(name = "操作管理员")
    var operator: String? = null

    @Schema(description = "是否启用")
    @Excel(name = "是否启用")
    @get:JvmName("getIsEnabled")
    @set:JvmName("setIsEnabled")
    var isEnabled: String? = null

    @Schema(description = "活动状态")
    @Excel(name = "活动状态")
    var state: String? = null

    @Schema(description = "是否删除")
    var delFlag: String? = null

    @Schema(description = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")
    var startTime: Date? = null

    @Schema(description = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")
    var endTime: Date? = null
    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", id)
            .append("activityId", activityId)
            .append("title", title)
            .append("content", content)
            .append("activityType", activityType)
            .append("regionCode", regionCode)
            .append("storeCount", storeCount)
            .append("usedStoreCount", usedStoreCount)
            .append("taxRatio", taxRatio)
            .append("operator", operator)
            .append("isEnabled", isEnabled)
            .append("state", state)
            .append("delFlag", delFlag)
            .append("startTime", startTime)
            .append("endTime", endTime)
            .append("createTime", createTime)
            .append("updateTime", updateTime)
            .toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
