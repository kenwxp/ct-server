package com.cloudtimes.thirdpart.services

import com.cloudtimes.thirdpart.dto.response.YcygSuggestionPurchase
import org.springframework.stereotype.Service

@Service
class RcygService {
    fun suggestionPurchase(customerId: String): List<YcygSuggestionPurchase> {
        return emptyList()
    }
}