package com.cloudtimes.common.core.domain;

import com.cloudtimes.common.constant.HttpStatus;
import com.cloudtimes.common.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 操作消息提醒
 *
 * @author tank
 */
@ApiModel(value = "ApiResult", description = "接口统一响应结构体")
@Data
@Builder
@Accessors(chain = true)
public class ApiResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    @ApiModelProperty("状态码")
    public int code;

    /**
     * 返回内容
     */
    @ApiModelProperty("状态消息")
    public String msg = "";
    /**
     * 总数（列表查询时返回）
     */
    @ApiModelProperty("总数（列表查询时返回）")
    public long total = 0L;
    /**
     * 数据对象
     */
    @ApiModelProperty("响应参数结构体")
    public T data;

    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    public ApiResult() {
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    public ApiResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public ApiResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        if (StringUtils.isNotNull(data)) {
            this.data = data;
        }
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public ApiResult(int code, String msg, long total, T data) {
        this.code = code;
        this.msg = msg;
        this.total = total;
        if (StringUtils.isNotNull(data)) {
            this.data = data;
        }
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static ApiResult success() {
        return ApiResult.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public ApiResult success(T data) {
        return new ApiResult().success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static ApiResult success(String msg) {
        return new ApiResult().success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public ApiResult success(String msg, T data) {
        return new ApiResult(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static ApiResult error() {
        return new ApiResult().error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public ApiResult error(String msg) {
        return error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public ApiResult error(String msg, T data) {
        return new ApiResult(HttpStatus.ERROR, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 警告消息
     */
    public ApiResult error(int code, String msg) {
        return new ApiResult(code, msg, null);
    }

}
