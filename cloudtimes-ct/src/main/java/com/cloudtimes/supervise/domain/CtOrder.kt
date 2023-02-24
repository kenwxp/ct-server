package com.cloudtimes.supervise.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.math.BigDecimal
import java.util.*

/**
 * 购物订单对象 ct_order
 *
 * @author wangxp
 * @date 2023-02-07
 */
@ApiModel(value = "CtOrder", description = "购物订单")
class CtOrder : BaseEntity() {
    @ApiModelProperty(value = "编号")
    var id: String? = null

    @ApiModelProperty(value = "任务编号")
    @Excel(name = "任务编号")
    var taskId: String? = null

    @ApiModelProperty(value = "门店ID")
    @Excel(name = "门店ID")
    var storeId: String? = null

    @ApiModelProperty(value = "门店名称")
    @Excel(name = "门店名称")
    var storeName: String? = null

    @ApiModelProperty(value = "门店省位置")
    @Excel(name = "门店省位置")
    var storeProvince: String? = null

    @ApiModelProperty(value = "门店市位置")
    @Excel(name = "门店市位置")
    var storeCity: String? = null

    @ApiModelProperty(value = "代理编号")
    @Excel(name = "代理编号")
    var agentId: String? = null

    @ApiModelProperty(value = "店老板编号")
    @Excel(name = "店老板编号")
    var bossUserId: String? = null

    @ApiModelProperty(value = "购物编号")
    @Excel(name = "购物编号")
    var shoppingId: String? = null

    @ApiModelProperty(value = "值守员工号")
    @Excel(name = "值守员工号")
    var staffCode: String? = null

    @ApiModelProperty(value = "顾客编号")
    @Excel(name = "顾客编号")
    var userId: String? = null

    @ApiModelProperty(value = "是否组合支付")
    @Excel(name = "是否组合支付")
    @get:JvmName("getIsCompose")
    @set:JvmName("setIsCompose")
    var isCompose: String? = null

    @ApiModelProperty(value = "现场支付")
    @Excel(name = "现场支付")
    var moneyAmount: BigDecimal? = null

    @ApiModelProperty(value = "应付金额")
    @Excel(name = "应付金额")
    var totalAmount: BigDecimal? = null

    @ApiModelProperty(value = "折扣金额")
    @Excel(name = "折扣金额")
    var discountAmount: BigDecimal? = null

    @ApiModelProperty(value = "抵扣金额")
    @Excel(name = "抵扣金额")
    var deductionAmount: BigDecimal? = null

    @ApiModelProperty(value = "实付金额")
    @Excel(name = "实付金额")
    var actualAmount: BigDecimal? = null

    @ApiModelProperty(value = "商品总数")
    @Excel(name = "商品总数")
    var itemCount: Long? = null

    @ApiModelProperty(value = "支付渠道编号")
    @Excel(name = "支付渠道编号")
    var paymentCode: String? = null

    @ApiModelProperty(value = "支付渠道名称")
    @Excel(name = "支付渠道名称")
    var paymentName: String? = null

    @ApiModelProperty(value = "支付方式")
    /**
     * 支付宝   0
     * 微信	   1
     * 网银	   2
     */
    @Excel(name = "支付方式")
    var paymentMode: String? = null

    @ApiModelProperty(value = "支付行为")
    /**
     * 扫码	0
     * 刷脸	1
     */
    @Excel(name = "支付行为")
    var paymentAction: String? = null

    @ApiModelProperty(value = "支付流水号")
    @Excel(name = "支付流水号")
    var paymentId: String? = null

    @ApiModelProperty(value = "收银机编号")
    @Excel(name = "收银机编号")
    var deviceCashId: String? = null

    @ApiModelProperty(value = "备注")
    @Excel(name = "备注")
    var descText: String? = null

    @ApiModelProperty(value = "是否异常")
    @Excel(name = "是否异常")

    @get:JvmName("getIsExceptional")
    @set:JvmName("setIsExceptional")
    var isExceptional: String? = null

    @ApiModelProperty(value = "订单状态")
    /** 未支付	0
     * 支付中	1
     * 支付完成	2
     * 支付失败	3
     * 已撤销	4
     */
    @Excel(name = "订单状态")
    var state: String? = null

    @ApiModelProperty(value = "是否删除")
    var delFlag: String? = null

    @ApiModelProperty(value = "订单年月")
    var yearMonth: Int? = null

    @ApiModelProperty(value = "创建日期")
    var createDate: Date? = null

    companion object {
        const val serialVersionUID = 1L
    }

    override fun toString(): String {
        return """CtOrder(
            id=$id, 
            taskId=$taskId, 
            storeId=$storeId, 
            storeName=$storeName, 
            storeProvince=$storeProvince, 
            storeCity=$storeCity, 
            agentId=$agentId,
            bossUserId=$bossUserId,
            shoppingId=$shoppingId,
            staffCode=$staffCode,
            userId=$userId,
            isCompose=$isCompose,
            moneyAmount=$moneyAmount,
            totalAmount=$totalAmount,
            discountAmount=$discountAmount,
            deductionAmount=$deductionAmount,
            actualAmount=$actualAmount,
            itemCount=$itemCount,
            paymentCode=$paymentCode,
            paymentName=$paymentName,
            paymentMode=$paymentMode,
            paymentAction=$paymentAction,
            paymentId=$paymentId,
            deviceCashId=$deviceCashId,
            descText=$descText,
            isExceptional=$isExceptional,
            state=$state,
            delFlag=$delFlag,
            yearMonth=$yearMonth,
            createDate=$createDate,
            createTime=$createTime,
            updateTime=$updateTime
        )""".trimIndent()
    }
}
