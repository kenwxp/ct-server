package com.cloudtimes.quartz.task

import com.cloudtimes.common.exception.ServiceException
import com.cloudtimes.system.service.ISysConfigService
import com.cloudtimes.thirdpart.dto.request.YcygBatchProductSyncRequest
import com.cloudtimes.thirdpart.dto.response.YcygBatchProductSyncResponse
import com.cloudtimes.thirdpart.services.RcygService
import org.springframework.stereotype.Component

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.client.RestTemplate

@Component("rcygTask")
class RcygTask {
    private var logger: Logger = LoggerFactory.getLogger(javaClass.simpleName)

    @Autowired
    private lateinit var rcygService: RcygService

    @Autowired
    private lateinit var restTemplate: RestTemplate

    @Autowired
    private lateinit var configService: ISysConfigService

    private fun batchProductSyncRequest(url: String, pageNo: Int, pageSize: Int): Boolean {
        val params = YcygBatchProductSyncRequest(pageNo = 1, size = 5)
        val response = restTemplate.postForObject(url,
            params,
            YcygBatchProductSyncResponse::class.java
        )
        logger.info("response: $response")

        val hasNextPage = response?.data?.hasNextPage ?: false
        val list = response?.data?.list ?: emptyList()

        rcygService.incrementalProductSync(list)

        return hasNextPage;
    }

    /** 批量同步蓉城易购商品 */
    fun batchProductSync() {
        val rcygDomain = configService.selectConfigByKey("rcyg_domain") ?:
            throw ServiceException("代理邀请地址参数未配置!")

        val batchServiceUrl = "$rcygDomain/goods/page"
        logger.info("batchServiceUrl = $batchServiceUrl")

        var hasNextPage = true
        var pageNo = 1
        while ( hasNextPage ) {
            hasNextPage = batchProductSyncRequest(batchServiceUrl, pageNo, 5)
            // :TODO: 调试完成后放开
            break
            pageNo += 1
        }
    }
}