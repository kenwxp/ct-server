package com.cloudtimes.account.service.impl

import com.cloudtimes.account.domain.CtTransferBook
import com.cloudtimes.account.mapper.CtTransferBookMapper
import com.cloudtimes.account.service.ICtTransferBookService
import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 转账登记簿Service业务层处理
 *
 * @author 沈兵
 * @date 2023-02-16
 */
@DataSource(DataSourceType.CT)
@Service
class CtTransferBookServiceImpl : ICtTransferBookService {
    @Autowired
    private val ctTransferBookMapper: CtTransferBookMapper? = null
}