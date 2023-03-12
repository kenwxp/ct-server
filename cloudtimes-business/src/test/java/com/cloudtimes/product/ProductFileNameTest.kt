package com.cloudtimes.product

import org.junit.jupiter.api.Test
import java.util.regex.Pattern

class ProductFileNameTest {
    @Test
    fun validateFileName() {
        val fileName = "CT20230200012_20221128_01.xlsx";
        val fileNamePattern = Pattern.compile("^(CT\\d{10,20})_(\\d{8})_(\\d{1,3}).xlsx$")
        val matcher = fileNamePattern.matcher(fileName);
        if (matcher.find()) {
            val storeNo = matcher.group(1)
            val uploadDate = matcher.group(2)
            val batchNo = matcher.group(3)
            println("storeNo: $storeNo")
            println("uploadDate: $uploadDate")
            println("batchNo: $batchNo")
        }
    }
}