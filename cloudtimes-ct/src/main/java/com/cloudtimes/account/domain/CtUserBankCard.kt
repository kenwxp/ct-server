package com.cloudtimes.account.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.util.*

/**
 * 用户银行卡对象 ct_user_bank_card
 *
 * @author 沈兵
 * @date 2023-02-03
 */
class CtUserBankCard : BaseEntity() {
    /** 编号  */
    var id: String? = null

    /** 用户编号  */
    @Excel(name = "用户编号")
    var userId: String? = null

    /** 用户类型  */
    @Excel(name = "用户类型")
    var userType: String? = null

    /** 开户行名称  */
    @Excel(name = "开户行名称")
    var openBankName: String? = null

    /** 持卡人姓名  */
    @Excel(name = "持卡人姓名")
    var userName: String? = null

    /** 预留手机号  */
    @Excel(name = "预留手机号")
    var mobile: String? = null

    /** 卡号  */
    @Excel(name = "卡号")
    var cardNo: String? = null

    /** 是否解绑  */
    @Excel(name = "是否解绑")
    var isUnbind: String? = null

    /** 是否删除  */
    var delFlag: String? = null

    /** 创建日期  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30.0, dateFormat = "yyyy-MM-dd")
    var createDate: Date? = null
    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", id)
            .append("userId", userId)
            .append("userType", userType)
            .append("openBankName", openBankName)
            .append("userName", userName)
            .append("mobile", mobile)
            .append("cardNo", cardNo)
            .append("remark", remark)
            .append("isUnbind", isUnbind)
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