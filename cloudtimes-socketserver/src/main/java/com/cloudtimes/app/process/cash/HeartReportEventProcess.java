package com.cloudtimes.app.process.cash;

import com.cloudtimes.app.process.BaseEventProcess;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 开门事件处理器
 */
@Slf4j
@Component("HEART-REPORT")
public class HeartReportEventProcess implements BaseEventProcess {

    @Override
    public String eventName() {
        return "收银机心跳状态处理";
    }

    @Override
    public Object process(AuthUser authUser, Object object) {

        //todo 更新收银机状态
        return AjaxResult.success();
    }
}
