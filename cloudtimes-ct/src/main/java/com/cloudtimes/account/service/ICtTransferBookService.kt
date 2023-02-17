package com.cloudtimes.account.service

import com.cloudtimes.account.domain.CtTransferBook
import com.cloudtimes.account.dto.response.TransferRecord

/**
 * 转账登记簿Service接口
 *
 * @author 沈兵
 * @date 2023-02-16
 */
interface ICtTransferBookService {
    fun selectTransferRecords(userId: String): List<TransferRecord>
}