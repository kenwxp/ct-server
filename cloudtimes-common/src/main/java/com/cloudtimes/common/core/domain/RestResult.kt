package com.cloudtimes.common.core.domain

import org.springframework.http.HttpStatus

/**
 * Rest结构集
 *
 * @author 沈兵
 */
open class RestResult<T> {
    var code: Int = 200
    var msg: String = "处理成功"
    open var data: T? = null

    constructor() : this(code = 200, msg = "处理成功", data = null)

    constructor(data: T?) : this(code = 200, msg = "处理成功", data = data)

    constructor(code: Int, msg: String, data: T?) {
        this.code = code
        this.msg = msg
        this.data = data
    }


    fun notFound(msg: String) {
        this.code = HttpStatus.NOT_FOUND.value()
        this.msg = msg;
    }

    override fun toString(): String {
        return "RestResult{code: $code, msg: $msg, data: $data}"
    }

}
