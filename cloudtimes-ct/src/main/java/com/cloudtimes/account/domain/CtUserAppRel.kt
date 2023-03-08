package com.cloudtimes.account.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

/**
 * 用户与应用关系对象 ct_user_app_rel
 *
 * @author 沈兵
 * @date 2023-03-08
 */
class CtUserAppRel : BaseEntity() {
    /** 用户编号  */
    var userId: String? = null

    /** 应用类型  */
    var appType: String? = null

    /** 应用用户编号，如微信的openId等  */
    @Excel(name = "应用用户编号，如微信的openId等")
    var appUserId: String? = null

    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", userId)
            .append("appType", appType)
            .append("appUserId", appUserId)
            .append("createTime", createTime)
            .append("updateTime", updateTime)
            .toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}