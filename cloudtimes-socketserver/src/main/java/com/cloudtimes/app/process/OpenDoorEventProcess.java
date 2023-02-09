package com.cloudtimes.app.process;

import com.cloudtimes.common.core.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 开门事件处理器
 */
@Slf4j
@Component("OPENDOOR")
public class OpenDoorEventProcess implements BaseEventProcess
{

    @Override
    public String eventName() {
        return "开门事件处理器";
    }

    @Override
    public Object process(Object object) {
        return AjaxResult.success();
    }
}
