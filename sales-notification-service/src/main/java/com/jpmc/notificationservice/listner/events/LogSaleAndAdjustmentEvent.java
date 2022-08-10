package com.jpmc.notificationservice.listner.events;

import org.springframework.context.ApplicationEvent;

public class LogSaleAndAdjustmentEvent<T> extends ApplicationEvent {
    private T entity;

    public LogSaleAndAdjustmentEvent(Object source, T entity) {
        super(source);
        this.entity = entity;
    }

    public T getEntity() {
        return entity;
    }
}
