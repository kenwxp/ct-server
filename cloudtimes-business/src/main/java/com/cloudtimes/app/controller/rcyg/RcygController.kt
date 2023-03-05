package com.cloudtimes.app.controller.rcyg

import com.cloudtimes.app.constant.PrefixPathConstants
import com.cloudtimes.common.core.domain.RestResult
import com.cloudtimes.thirdpart.dto.request.ByCustomerIdRequest
import com.cloudtimes.thirdpart.dto.request.YcygPurchaseBookKeepRequest
import com.cloudtimes.thirdpart.dto.response.YcygPurchaseBookKeepResponse
import com.cloudtimes.thirdpart.dto.response.YcygSuggestionPurchase
import com.cloudtimes.thirdpart.services.RcygService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

data class SuggestPurchaseList(val list: List<YcygSuggestionPurchase>)
class SuggestPurchaseResponse : RestResult<SuggestPurchaseList>()

@RestController
@RequestMapping(PrefixPathConstants.THIRD_PART_YCYG_PREFIX)
@Api(tags = ["第三方-蓉城易购"])
class RcygController(
    private val ycygServcie: RcygService
) {
    @PostMapping(value = ["/suggest_purchase"])
    @ApiOperation(value = "建议采购商品")
    fun suggestPurchase(@Valid @RequestBody request: ByCustomerIdRequest): SuggestPurchaseResponse {
        val suggestionPurchase = ycygServcie.suggestionPurchase(request.customerId)
        return SuggestPurchaseResponse().apply {
            code = 0
            data = SuggestPurchaseList(suggestionPurchase)
        }
    }

    @PostMapping(value = ["/purchase_book_keep"])
    @ApiOperation(value = "采购商品入库")
    fun purchaseBookKeep(@Valid @RequestBody request: YcygPurchaseBookKeepRequest): YcygPurchaseBookKeepResponse {
        return YcygPurchaseBookKeepResponse()
    }

    @PostMapping(value = ["/incremental_product_sync"])
    @ApiOperation(value = "增量商品同步")
    fun incrementalProductSync(@Valid @RequestBody request: YcygPurchaseBookKeepRequest): YcygPurchaseBookKeepResponse {
        return YcygPurchaseBookKeepResponse()
    }
}