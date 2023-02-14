package com.cloudtimes.supervise.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.util.*

/**
 * 事件对象 ct_events
 *
 * @author 沈兵
 * @date 2023-02-14
 */
@ApiModel(value = "CtEvents", description = "事件/消息")
class CtEvents : BaseEntity() {
    @ApiModelProperty(value = "编号")
    var id: String? = null

    @ApiModelProperty(value = "事件类型")
    @Excel(name = "事件类型")
    var eventType: String? = null

    @ApiModelProperty(value = "事件名称")
    @Excel(name = "事件名称")
    var eventName: String? = null

    @ApiModelProperty(value = "事件详情")
    @Excel(name = "事件详情")
    var context: String? = null

    @ApiModelProperty(value = "发送者用户编码")
    @Excel(name = "发送者用户编码")
    var sender: String? = null

    @ApiModelProperty(value = "接收者昵称/名称")
    @Excel(name = "接收者昵称/名称")
    var senderName: String? = null

    @ApiModelProperty(value = "接收者用户编码")
    @Excel(name = "接收者用户编码")
    var receiver: String? = null

    @ApiModelProperty(value = "接收者昵称/名称")
    @Excel(name = "接收者昵称/名称")
    var receiverName: String? = null

    @ApiModelProperty(value = "任务编号")
    @Excel(name = "任务编号")
    var taskId: String? = null

    @ApiModelProperty(value = "购物编号")
    @Excel(name = "购物编号")
    var shoppingId: String? = null

    @ApiModelProperty(value = "用户类型")
    @Excel(name = "用户类型")
    var userType: String? = null

    @ApiModelProperty(value = "应用端类型")
    @Excel(name = "应用端类型")
    var sourceType: String? = null

    @ApiModelProperty(value = "是否结束")
    @Excel(name = "是否结束")
    var isStopped: String? = null

    @ApiModelProperty(value = "有效期")
    @Excel(name = "有效期")
    var validDays: Long? = null

    @ApiModelProperty(value = "创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30.0, dateFormat = "yyyy-MM-dd")
    var createDate: Date? = null

    @ApiModelProperty(value = "是否删除")
    var delFlag: String? = null
    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", id)
            .append("eventType", eventType)
            .append("eventName", eventName)
            .append("context", context)
            .append("sender", sender)
            .append("senderName", senderName)
            .append("receiver", receiver)
            .append("receiverName", receiverName)
            .append("taskId", taskId)
            .append("shoppingId", shoppingId)
            .append("userType", userType)
            .append("sourceType", sourceType)
            .append("isStopped", isStopped)
            .append("validDays", validDays)
            .append("createDate", createDate)
            .append("createTime", createTime)
            .append("updateTime", updateTime)
            .append("delFlag", delFlag)
            .toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
