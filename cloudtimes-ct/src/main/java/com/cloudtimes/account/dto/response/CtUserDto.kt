package com.cloudtimes.account.dto.response

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

/**
 * 用户对象 ct_user
 *
 * @author 沈兵
 * @date 2023-01-17
 */
@Schema(description = "用户信息")
class CtUserDto : BaseEntity() {
    @Schema(description = "序列号")
    var id: String? = null

    @Schema(description = "登录账号")
    @Excel(name = "登录账号")
    var account: String? = null

    @Schema(description = "微信openId")
    @Excel(name = "微信openId")
    var wxOpenId: String? = null

    @Schema(description = "微信unionId")
    @Excel(name = "微信unionId")
    var wxUnionId: String? = null

    @Schema(description = "微信头像")
    @Excel(name = "微信头像")
    var wxAvatar: String? = null

    @Schema(description = "登录密码")
    @Excel(name = "登录密码")
    var password: String? = null

    @Schema(description = "电话号码")
    @Excel(name = "电话号码")
    var mobile: String? = null

    @Schema(description = "昵称")
    var nickName: String? = null

    @Schema(description = "真实姓名")
    @Excel(name = "真实姓名")
    var realName: String? = null

    @Schema(description = "性别")
    @Excel(name = "性别")
    var sex: String? = null

    @Schema(description = "生日")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "生日", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")
    var birthday: Date? = null

    @Schema(description = "是否实名")
    /**
     * 否	0
     * 是	1
     */
    @Excel(name = "是否实名")
    @get:JvmName("getIsReal")
    @set:JvmName("setIsReal")
    var isReal: String? = null

    @Schema(description = "违规次数")
    @Excel(name = "违规次数")
    var violationCount: Long? = null

    @Schema(description = "身份证号")
    @Excel(name = "身份证号")
    var idNo: String? = null

    @Schema(description = "是否代理")
    /**
     * 否	0
     * 是	1
     */
    @Excel(name = "是否代理")
    @set:JvmName("setIsAgent")
    @get:JvmName("getIsAgent")
    var isAgent: String? = null

    @Schema(description = "注册门店编号")
    @Excel(name = "注册门店编号")
    var registerStoreId: String? = null

    @Schema(description = "是否店老板")
    /**
     * 否	0
     * 是	1
     */
    @Excel(name = "是否店老板")
    @get:JvmName("getIsShopBoss")
    @set:JvmName("setIsShopBoss")
    var isShopBoss: String? = null

    @Schema(description = "最近登录IP")
    @Excel(name = "最近登录IP")
    var lastLoginIp: String? = null

    @Schema(description = "最近登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    @Excel(name = "最近登录时间", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")
    var lastLoginTime: Date? = null

    @Schema(description = "成为店主时间")
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    @Excel(name = "成为店主时间", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")
    var createBossTime: Date? = null

    @Schema(description = "成为代理时间")
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    @Excel(name = "成为代理时间", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")
    var createAgentTime: Date? = null

    @Schema(description = "代理状态")
    /**
     * 未开通	0
     * 签约中	1
     * 已开通	2
     * 已停用	3
     */
    @Excel(name = "代理状态")
    var agentState: String? = null

    @Schema(description = "代理类型")
    /**
     * 城市合伙人	0
     * 特约合伙人	1
     * 普通代理	2
     */
    @Excel(name = "代理类型")
    var agentType: String? = null

    @Schema(description = "店主状态")
    /**
     * 未开通	0
     * 签约中	1
     * 已开通	2
     * 已停用	3
     */
    @Excel(name = "店主状态")
    var bossState: String? = null

    @Schema(description = "顾客状态")
    /**
     * 正常	0
     * 违规	1
     * 禁用	2
     */
    @Excel(name = "顾客状态")
    var customerState: String? = null

    @Schema(description = "操作管理员")
    @Excel(name = "操作管理员")
    var operator: String? = null

    @Schema(description = "注册日期")
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    @Excel(name = "注册日期", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")
    var createDate: Date? = null

    @Schema(description = "是否删除")
            /**
             * 否	0
             * 是	1
             */
    var delFlag: String? = null

    /** token: 不体现在数据库中，只是用于和前段交互  */
    var token: String? = null

    /** 上级代理ID*/
    var parentUserId: String? = null

    /** 上级代理手机*/
    var parentMobile: String? = null

    /** 上级代理真实姓名*/
    var parentRealName: String? = null

    companion object {
        const val serialVersionUID = 1L
    }
}
