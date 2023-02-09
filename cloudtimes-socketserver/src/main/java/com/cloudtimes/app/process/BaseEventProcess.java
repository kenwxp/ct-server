package com.cloudtimes.app.process;

public interface BaseEventProcess {

    public String eventName();

    public Object process(Object object);
}
