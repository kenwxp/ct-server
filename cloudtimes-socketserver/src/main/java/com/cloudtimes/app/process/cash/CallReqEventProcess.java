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
@Component("CALL-REQ")
public class CallReqEventProcess implements BaseEventProcess {

    @Override
    public String eventName() {
        return "收银机呼叫客服处理";
    }

    @Override
    public Object process(AuthUser authUser, Object object) {
        //todo 推送消息到后台
        return AjaxResult.success();
    }
}
