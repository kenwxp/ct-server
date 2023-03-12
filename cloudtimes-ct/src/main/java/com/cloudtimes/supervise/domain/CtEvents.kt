package com.cloudtimes.supervise.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.util.*

/**
 * 事件对象 ct_events
 *
 * @author 沈兵
 * @date 2023-02-14
 */
@Schema(description = "事件/消息")
class CtEvents : BaseEntity() {
    @Schema(description = "编号")
    var id: String? = null

    /**
     *   -- 0 系统公告
     *   -- 1 系统消息
     *   -- 2 会员消息
     *   -- 3 偷盗异常
     *   -- 4 物品追回
     *   -- 5 代理消息
     *   -- 6 店主消息
     *   -- 7 客服消息
     */
    @Schema(description = "事件类型")
    @Excel(name = "事件类型")
    var eventType: String? = null

    /**
     *   -- 51-系统消息(授权提示等)，52-佣金消息, 53-活动消息, 54-分润消息，55-提现消息
     */
    @Schema(description = "事件子类型")
    @Excel(name = "事件子类型")
    var subType: String? = null

    @Schema(description = "事件名称")
    @Excel(name = "事件名称")
    var eventName: String? = null

    @Schema(description = "事件详情")
    @Excel(name = "事件详情")
    var content: String? = null

    @Schema(description = "发送者用户编码")
    @Excel(name = "发送者用户编码")
    var sender: String? = null

    @Schema(description = "接收者昵称/名称")
    @Excel(name = "接收者昵称/名称")
    var senderName: String? = null

    @Schema(description = "接收者用户编码")
    @Excel(name = "接收者用户编码")
    var receiver: String? = null

    @Schema(description = "接收者昵称/名称")
    @Excel(name = "接收者昵称/名称")
    var receiverName: String? = null

    @Schema(description = "任务编号")
    @Excel(name = "任务编号")
    var taskId: String? = null

    @Schema(description = "购物编号")
    @Excel(name = "购物编号")
    var shoppingId: String? = null

    @Schema(description = "用户类型")
    @Excel(name = "用户类型")
    var userType: String? = null

    @Schema(description = "应用端类型")
    @Excel(name = "应用端类型")
    var sourceType: String? = null

    @Schema(description = "是否结束")
    @Excel(name = "是否结束")
    var isStopped: String? = null

    @Schema(description = "有效期")
    @Excel(name = "有效期")
    var validDays: Long? = null

    @Schema(description = "创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30.0, dateFormat = "yyyy-MM-dd")
    var createDate: Date? = null

    @Schema(description = "是否删除")
    var delFlag: String? = null

    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", id)
            .append("eventType", eventType)
            .append("eventName", eventName)
            .append("content", content)
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
