package com.cloudtimes.app.event;

import com.cloudtimes.app.models.MessagEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 开门事件处理器
 */
@Slf4j
@Component
public class OpenDoorEventProcess implements ApplicationListener<MessagEvent> {
    @Override
    public void onApplicationEvent(MessagEvent event) {

    }
}
