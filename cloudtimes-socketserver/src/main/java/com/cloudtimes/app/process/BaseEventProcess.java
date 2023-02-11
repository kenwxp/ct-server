package com.cloudtimes.app.process;

import com.cloudtimes.common.core.domain.entity.AuthUser;

public interface BaseEventProcess {

    public String eventName();

    public Object process(AuthUser user, Object data);
}