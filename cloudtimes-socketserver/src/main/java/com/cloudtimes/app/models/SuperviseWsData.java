package com.cloudtimes.app.models;

import com.cloudtimes.common.constant.HttpStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理端长链接统一返回体")
@Data
public class SuperviseWsData<T> {
    @Schema(description = "指令类型")
    private String option;
    @Schema(description = "状态码")
    private int code;
    @Schema(description = "状态消息")
    private String msg;
    @Schema(description = "业务数据")
    private T data;

    public SuperviseWsData() {
    }

    public SuperviseWsData(String option, int code, String msg, T data) {
        this.option = option;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public SuperviseWsData success(String option) {
        return success(option, null);
    }

    public SuperviseWsData success(String option, T data) {
        return new SuperviseWsData(option, HttpStatus.SUCCESS, "操作成功", data);
    }

    public SuperviseWsData error(String option) {
        return error(option, "操作失败");
    }

    public SuperviseWsData error(String option, String msg) {
        return error(option, msg, null);
    }

    public SuperviseWsData error(String option, T data) {
        return error(option, "操作失败", data);
    }

    public SuperviseWsData error(String option, String msg, T data) {
        return new SuperviseWsData(option, HttpStatus.ERROR, msg, data);
    }

}
