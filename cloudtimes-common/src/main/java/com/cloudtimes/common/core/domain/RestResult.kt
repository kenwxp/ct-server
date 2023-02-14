package com.cloudtimes.common.core.domain
/**
 * Rest结构集
 *
 * @author 沈兵
 */
open class RestResult<T> {
    var code: Int = 200
    var msg: String = "处理成功"
    var total: Int? = null
    open var data: T? = null

    constructor() : this(code = 200, msg = "处理成功", total = null, data = null)

    constructor(data: T?) : this(code = 200, msg = "处理成功", total = null, data = data)

    constructor(code: Int, msg: String, total: Int?, data: T?) {
        this.code = code
        this.msg = msg
        this.total = total
        this.data = data
    }

    override fun toString(): String {
        return "RestResult{code: $code, msg: $msg, total: $total, data: $data}"
    }
}
