package com.cloudtimes.thirdpart.services

import com.cloudtimes.common.enums.ChannelType
import com.cloudtimes.product.domain.CtProductThird
import com.cloudtimes.product.mapper.CtProductCatalogMapper
import com.cloudtimes.product.mapper.CtProductThirdMapper
import com.cloudtimes.product.mapper.provider.CtProductCatalogProvider
import com.cloudtimes.product.mapper.provider.CtProductThirdProvider
import com.cloudtimes.thirdpart.dto.request.RcygProductRecord
import com.cloudtimes.thirdpart.dto.response.YcygSuggestionPurchase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Service
class RcygService {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private lateinit var catalogMapper: CtProductCatalogMapper

    @Autowired
    private lateinit var thirdMapper: CtProductThirdMapper

    /** 增量商品同步 */
    fun incrementalProductSync(list: List<RcygProductRecord>): Int {
        var updatedRecords = 0
        for (record in list) {
            // 查询商品是否存在
            val existCatalog = catalogMapper.selectOne(
                CtProductCatalogProvider.findByBarCode(record.barcode)
            )

            //  如果商品不存在，新增商品
            if ( existCatalog == null ) {
                catalogMapper.generalInsert(CtProductCatalogProvider.insertRcygProduct(record))
            } else {
                logger.debug("已经存在: $record")
            }

            // 查询第三方商品是否存在
            val existThird = thirdMapper.selectOne(
                CtProductThirdProvider.findByThirdAndBarcode(
                    ChannelType.THIRD_RCYG.code,
                    record.barcode
                )
            )

            //  如果第三方商品不存在，新增第三方商品
            if (existThird == null) {
                val thirdProduct = CtProductThird().apply {
                    thirdCode = ChannelType.THIRD_RCYG.code
                    thirdProductId = record.thirdProductId
                    barcode = record.barcode
                }
                thirdMapper.generalInsert(CtProductThirdProvider.insertThirdProduct(thirdProduct))
            } else {
                if (existThird.thirdProductId != record.thirdProductId) {
                    logger.error("RCYG: 存在同一barcode对应不同产品的情况, $record")
                }
            }

            updatedRecords++
        }

        return updatedRecords
    }

    fun suggestionPurchase(customerId: String): List<YcygSuggestionPurchase> {
        return emptyList()
    }
}