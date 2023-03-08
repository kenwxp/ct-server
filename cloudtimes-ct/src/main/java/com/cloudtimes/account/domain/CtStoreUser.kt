package com.cloudtimes.account.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.util.*

/**
 * 门店会员对象 ct_store_user
 *
 * @author 沈兵
 * @date 2023-03-08
 */
class CtStoreUser : BaseEntity() {
    /** 门店编号  */
    var storeId: String? = null

    /** 门店名称  */
    @Excel(name = "门店名称")
    var storeName: String? = null

    /** 会员编号  */
    var userId: String? = null

    /** 会员昵称  */
    @Excel(name = "会员昵称")
    var nickName: String? = null

    /** 会员类型  */
    @Excel(name = "会员类型")
    var vipType: String? = null

    /** 是否删除  */
    var delFlag: String? = null

    /** 创建日期  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30.0, dateFormat = "yyyy-MM-dd")
    var createDate: Date? = null

    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("storeId", storeId)
            .append("storeName", storeName)
            .append("userId", userId)
            .append("nickName", nickName)
            .append("vipType", vipType)
            .append("delFlag", delFlag)
            .append("createDate", createDate)
            .append("createTime", createTime)
            .append("updateTime", updateTime)
            .toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}