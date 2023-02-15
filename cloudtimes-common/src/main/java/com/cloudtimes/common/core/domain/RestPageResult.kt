package com.cloudtimes.common.core.domain

/**
 * Rest结构集
 *
 * @author 沈兵
 */
open class RestPageResult<T> {
    var code: Int = 200
    var msg: String = "处理成功"
    var total: Long = 0
    var rows: List<T> = emptyList<T>()

    constructor() : this(code = 200, msg = "处理成功", total = 0, rows = emptyList<T>())

    constructor(code: Int, msg: String, total: Long, rows: List<T>) {
        this.code = code
        this.msg = msg
        this.total = total
        this.rows = rows
    }


    override fun toString(): String {
        return "RestResult{code: $code, msg: $msg, total: $total, rows: $rows}"
    }
}
