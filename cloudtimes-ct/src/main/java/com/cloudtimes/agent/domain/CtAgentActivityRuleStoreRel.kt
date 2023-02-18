package com.cloudtimes.agent.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.util.*

/**
 * 代理活动规则与店铺关系对象 ct_agent_activity_rule_store_rel
 *
 * @author 沈兵
 * @date 2023-02-17
 */
@ApiModel(value = "CtAgentActivityRuleStoreRel", description = "代理活动规则与店铺关系")
class CtAgentActivityRuleStoreRel : BaseEntity() {
    @ApiModelProperty(value = "编号")
    var id: String? = null

    @ApiModelProperty(value = "活动规则编号")
    @Excel(name = "活动规则编号")
    var activityRuleId: String? = null

    @ApiModelProperty(value = "店铺编号")
    @Excel(name = "店铺编号")
    var storeId: String? = null

    @ApiModelProperty(value = "门店上线日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "门店上线日期", width = 30.0, dateFormat = "yyyy-MM-dd")
    var storeOnlineDate: Date? = null

    @ApiModelProperty(value = "门店所属地区码")
    @Excel(name = "门店所属地区码")
    var regionCode: String? = null

    @ApiModelProperty(value = "上级代理用户编号")
    @Excel(name = "上级代理用户编号")
    var parentAgentId: String? = null

    @ApiModelProperty(value = "代理用户编号")
    @Excel(name = "代理用户编号")
    var agentId: String? = null

    @ApiModelProperty(value = "是否删除")
    var delFlag: String? = null
    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", id)
            .append("activityRuleId", activityRuleId)
            .append("storeId", storeId)
            .append("storeOnlineDate", storeOnlineDate)
            .append("regionCode", regionCode)
            .append("parentAgentId", parentAgentId)
            .append("agentId", agentId)
            .append("delFlag", delFlag)
            .append("createTime", createTime)
            .append("updateTime", updateTime)
            .toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
