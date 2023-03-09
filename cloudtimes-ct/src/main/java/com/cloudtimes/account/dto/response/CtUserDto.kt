package com.cloudtimes.account.dto.response

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.*

/**
 * 用户对象 ct_user
 *
 * @author 沈兵
 * @date 2023-01-17
 */
@ApiModel(value = "CtUserDto", description = "用户信息")
class CtUserDto : BaseEntity() {
    @ApiModelProperty(value = "序列号")
    var id: String? = null

    @ApiModelProperty(value = "登录账号")
    @Excel(name = "登录账号")
    var account: String? = null

    @ApiModelProperty(value = "微信openId")
    @Excel(name = "微信openId")
    var wxOpenId: String? = null

    @ApiModelProperty(value = "微信unionId")
    @Excel(name = "微信unionId")
    var wxUnionId: String? = null

    @ApiModelProperty(value = "微信头像")
    @Excel(name = "微信头像")
    var wxAvatar: String? = null

    @ApiModelProperty(value = "登录密码")
    @Excel(name = "登录密码")
    var password: String? = null

    @ApiModelProperty(value = "电话号码")
    @Excel(name = "电话号码")
    var mobile: String? = null

    @ApiModelProperty(value = "昵称")
    var nickName: String? = null

    @ApiModelProperty(value = "真实姓名")
    @Excel(name = "真实姓名")
    var realName: String? = null

    @ApiModelProperty(value = "性别")
    @Excel(name = "性别")
    var sex: String? = null

    @ApiModelProperty(value = "生日")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "生日", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")
    var birthday: Date? = null

    @ApiModelProperty(value = "是否实名")
    /**
     * 否	0
     * 是	1
     */
    @Excel(name = "是否实名")
    @get:JvmName("getIsReal")
    @set:JvmName("setIsReal")
    var isReal: String? = null

    @ApiModelProperty(value = "违规次数")
    @Excel(name = "违规次数")
    var violationCount: Long? = null

    @ApiModelProperty(value = "身份证号")
    @Excel(name = "身份证号")
    var idNo: String? = null

    @ApiModelProperty(value = "是否代理")
    /**
     * 否	0
     * 是	1
     */
    @Excel(name = "是否代理")
    @set:JvmName("setIsAgent")
    @get:JvmName("getIsAgent")
    var isAgent: String? = null

    @ApiModelProperty(value = "注册门店编号")
    @Excel(name = "注册门店编号")
    var registerStoreId: String? = null

    @ApiModelProperty(value = "是否店老板")
    /**
     * 否	0
     * 是	1
     */
    @Excel(name = "是否店老板")
    @get:JvmName("getIsShopBoss")
    @set:JvmName("setIsShopBoss")
    var isShopBoss: String? = null

    @ApiModelProperty(value = "最近登录IP")
    @Excel(name = "最近登录IP")
    var lastLoginIp: String? = null

    @ApiModelProperty(value = "最近登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    @Excel(name = "最近登录时间", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")
    var lastLoginTime: Date? = null

    @ApiModelProperty(value = "成为店主时间")
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    @Excel(name = "成为店主时间", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")
    var createBossTime: Date? = null

    @ApiModelProperty(value = "成为代理时间")
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    @Excel(name = "成为代理时间", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")
    var createAgentTime: Date? = null

    @ApiModelProperty(value = "代理状态")
    /**
     * 未开通	0
     * 签约中	1
     * 已开通	2
     * 已停用	3
     */
    @Excel(name = "代理状态")
    var agentState: String? = null

    @ApiModelProperty(value = "代理类型")
    /**
     * 城市合伙人	0
     * 特约合伙人	1
     * 普通代理	2
     */
    @Excel(name = "代理类型")
    var agentType: String? = null

    @ApiModelProperty(value = "店主状态")
    /**
     * 未开通	0
     * 签约中	1
     * 已开通	2
     * 已停用	3
     */
    @Excel(name = "店主状态")
    var bossState: String? = null

    @ApiModelProperty(value = "顾客状态")
    /**
     * 正常	0
     * 违规	1
     * 禁用	2
     */
    @Excel(name = "顾客状态")
    var customerState: String? = null

    @ApiModelProperty(value = "操作管理员")
    @Excel(name = "操作管理员")
    var operator: String? = null

    @ApiModelProperty(value = "注册日期")
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    @Excel(name = "注册日期", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")
    var createDate: Date? = null

    @ApiModelProperty(value = "是否删除")
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
