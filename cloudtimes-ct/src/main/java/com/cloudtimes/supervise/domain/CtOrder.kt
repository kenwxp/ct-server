package com.cloudtimes.supervise.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import java.util.*

/**
 * 购物订单对象 ct_order
 *
 * @author wangxp
 * @date 2023-02-07
 */
@Schema(description = "购物订单")
class CtOrder : BaseEntity() {
    @Schema(description = "编号")
    var id: String? = null

    @Schema(description = "任务编号")
    @Excel(name = "任务编号")
    var taskId: String? = null

    @Schema(description = "门店ID")
    @Excel(name = "门店ID")
    var storeId: String? = null

    @Schema(description = "门店名称")
    @Excel(name = "门店名称")
    var storeName: String? = null

    @Schema(description = "门店省位置")
    @Excel(name = "门店省位置")
    var storeProvince: String? = null

    @Schema(description = "门店市位置")
    @Excel(name = "门店市位置")
    var storeCity: String? = null

    @Schema(description = "代理编号")
    @Excel(name = "代理编号")
    var agentId: String? = null

    @Schema(description = "店老板编号")
    @Excel(name = "店老板编号")
    var bossUserId: String? = null

    @Schema(description = "购物编号")
    @Excel(name = "购物编号")
    var shoppingId: String? = null

    @Schema(description = "值守员工号")
    @Excel(name = "值守员工号")
    var staffCode: String? = null

    @Schema(description = "顾客编号")
    @Excel(name = "顾客编号")
    var userId: String? = null

    @Schema(description = "顾客手机(内存冗余)")
    @Excel(name = "顾客手机(内存冗余)")
    var userPhone: String? = null

    @Schema(description = "是否组合支付")
    @Excel(name = "是否组合支付")
    @get:JvmName("getIsCompose")
    @set:JvmName("setIsCompose")
    var isCompose: String? = null

    @Schema(description = "现场支付")
    @Excel(name = "现场支付")
    var moneyAmount: BigDecimal? = null

    @Schema(description = "应付金额")
    @Excel(name = "应付金额")
    var totalAmount: BigDecimal? = null

    @Schema(description = "折扣金额")
    @Excel(name = "折扣金额")
    var discountAmount: BigDecimal? = null

    @Schema(description = "抵扣金额")
    @Excel(name = "抵扣金额")
    var deductionAmount: BigDecimal? = null

    @Schema(description = "实付金额")
    @Excel(name = "实付金额")
    var actualAmount: BigDecimal? = null

    @Schema(description = "商品总数")
    @Excel(name = "商品总数")
    var itemCount: Long? = null

    @Schema(description = "支付渠道编号")
    @Excel(name = "支付渠道编号")
    var paymentCode: String? = null

    @Schema(description = "支付渠道名称")
    @Excel(name = "支付渠道名称")
    var paymentName: String? = null

    @Schema(description = "支付方式")
    /**
     * 支付宝   0
     * 微信	   1
     * 网银	   2
     */
    @Excel(name = "支付方式")
    var paymentMode: String? = null

    @Schema(description = "支付行为")
    /**
     * 扫码	0
     * 刷脸	1
     */
    @Excel(name = "支付行为")
    var paymentAction: String? = null

    @Schema(description = "支付流水号")
    @Excel(name = "支付流水号")
    var paymentId: String? = null

    @Schema(description = "收银机编号")
    @Excel(name = "收银机编号")
    var deviceCashId: String? = null

    @Schema(description = "备注")
    @Excel(name = "备注")
    var descText: String? = null

    @Schema(description = "是否异常")
    @Excel(name = "是否异常")

    @get:JvmName("getIsExceptional")
    @set:JvmName("setIsExceptional")
    var isExceptional: String? = null

    @Schema(description = "订单状态")
    /** 未支付	0
     * 支付中	1
     * 支付完成	2
     * 支付失败	3
     * 已撤销	4
     */
    @Excel(name = "订单状态")
    var state: String? = null

    @Schema(description = "是否删除")
    var delFlag: String? = null

    @Schema(description = "订单年月")
    var yearMonth: Int? = null

    @Schema(description = "创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
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
