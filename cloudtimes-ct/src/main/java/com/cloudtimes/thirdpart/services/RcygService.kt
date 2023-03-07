package com.cloudtimes.thirdpart.services

import com.cloudtimes.common.enums.ChannelType
import com.cloudtimes.common.exception.ServiceException
import com.cloudtimes.hardwaredevice.mapper.CtStoreMapper
import com.cloudtimes.hardwaredevice.mapper.provider.CTStoreProvider
import com.cloudtimes.product.domain.CtProductThird
import com.cloudtimes.product.domain.CtShopProduct
import com.cloudtimes.product.domain.CtShopPurchase
import com.cloudtimes.product.mapper.CtProductCatalogMapper
import com.cloudtimes.product.mapper.CtProductThirdMapper
import com.cloudtimes.product.mapper.CtShopProductMapper
import com.cloudtimes.product.mapper.CtShopPurchaseMapper
import com.cloudtimes.product.mapper.provider.CtProductCatalogProvider
import com.cloudtimes.product.mapper.provider.CtProductThirdProvider
import com.cloudtimes.product.mapper.provider.CtShopProductProvider
import com.cloudtimes.product.mapper.provider.CtShopPurchaseProvider
import com.cloudtimes.thirdpart.dto.request.RcygProductRecord
import com.cloudtimes.thirdpart.dto.request.YcygPurchaseBookKeepRequest
import com.cloudtimes.thirdpart.dto.response.YcygSuggestPurchase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.transaction.annotation.Transactional

@Service
class RcygService {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private lateinit var storeMapper: CtStoreMapper

    @Autowired
    private lateinit var catalogMapper: CtProductCatalogMapper

    @Autowired
    private lateinit var shopProductMapper: CtShopProductMapper

    @Autowired
    private lateinit var purchaseMapper: CtShopPurchaseMapper

    @Autowired
    private lateinit var thirdMapper: CtProductThirdMapper

    /** 增量商品同步 */
    fun incrementalProductSync(list: List<RcygProductRecord>): Int {
        var updatedRows = 0
        for (record in list) {
            // Step 1.0 忽略条码为空的情况
            if (record.barcode.isNullOrEmpty() ) {
                continue
            }

            // Step 1.1 查询商品是否存在
            val existCatalog = catalogMapper.selectOne(
                CtProductCatalogProvider.findByBarCode(record.barcode!!)
            )

            //  Step 1.2 如果商品不存在，新增商品
            if ( existCatalog == null && !record.barcode.isNullOrEmpty() ) {
                catalogMapper.generalInsert(CtProductCatalogProvider.insertRcygProduct(record))
            } else {
                logger.debug("已经存在: $record")
            }

            // Step 2.1 查询第三方商品是否存在
            val existThird = thirdMapper.selectOne(
                CtProductThirdProvider.findByThirdAndBarcode(
                    ChannelType.THIRD_RCYG.code,
                    record.barcode!!
                )
            )

            //  Step 2.2 如果第三方商品不存在，新增第三方商品
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

            updatedRows++
        }

        return updatedRows
    }

    /** 查询店铺建议采购商品 */
    fun suggestionPurchase(customerId: String): List<YcygSuggestPurchase> {
        return shopProductMapper.selectSuggestProducts(
            CtShopProductProvider.findSuggestProductsStmt(customerId)
        )
    }

    /** 校验店铺商铺是否存在, 并增加不存在的商品 */
    fun verifyShopProducts(storeNo: String, productList: List<RcygProductRecord>) {
        var barcodes = productList.map { it.barcode!! }
        var existsBarcodes = shopProductMapper.selectManyProductBarcodes(
            CtShopProductProvider.findExistBarcodesStmt(storeNo, barcodes)
        )

        val newProductList = productList.filter { p ->
            existsBarcodes.none { barcode -> p.barcode == barcode }
        }.map { p -> p.toShopProduct(storeNo) }

        logger.info("newProductList: $newProductList")

        newProductList.forEach{ newProduct ->
            shopProductMapper.generalInsert(
                CtShopProductProvider.insertNewProductStmt(newProduct)
            )
        }
    }

    fun isShopPurchaseRecorded(purchase: CtShopPurchase) : Boolean {
        val existPurchase = purchaseMapper.selectOne(
            CtShopPurchaseProvider.selectPurchaseStmt(purchase)
        )

        return if (existPurchase != null) {
            true
        } else {
            purchaseMapper.generalInsert(
                CtShopPurchaseProvider.insertNewPurchaseStmt(purchase)
            )
            false
        }
    }

    @Transactional
    fun updateShopProductStock(shopProductList: List<CtShopProduct>): Int {
        for (shopProduct in shopProductList) {
            shopProductMapper.update(
                CtShopProductProvider.updatePurchaseProductStmt(shopProduct)
            )
        }

        return shopProductList.size
    }


    /* 采购商品入库 */
    fun purchaseBookKeep(request: YcygPurchaseBookKeepRequest): Int {
        // Step 1. 获取店铺信息
        val store = storeMapper.selectOne(CTStoreProvider.selectRcygStore(request.customerId))
            ?: throw ServiceException("RCYG 店铺 ${request.customerId} 不存在")

        // Step 2. 检查入库记计录是否存在
        if ( isShopPurchaseRecorded(request.toStorePurchase(store)) ) {
            return 0
        }

        // Step 3. 忽略赠品信息
        val purchaseList =request.list.filter { !it.barCode.isNullOrEmpty() }
        val productList = purchaseList.map { it.toRcygProduct() }

        // Step 3. 检查/增加商品目录
        incrementalProductSync(productList)

        // Step 4. 校验店铺商铺是否存在
        verifyShopProducts(store.storeNo!!, productList)

        // Step 5. 更新库存(事物)
        val shopProductList = purchaseList.map { it.toShopProduct(store.storeNo!!) }
        return updateShopProductStock(shopProductList)
    }
}