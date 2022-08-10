package com.jpmc.notificationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.jpmc.notificationservice.enums.ReportType;
import com.jpmc.notificationservice.listner.events.LogSaleAndAdjustmentEvent;
import com.jpmc.notificationservice.listner.events.LogSalesEvent;

@Service
public class LoggingService {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishReport(Object eventToPublish, ReportType typeOfEvent) {

        switch (typeOfEvent) {
            case LOG_SALE:
                applicationEventPublisher.publishEvent(new LogSalesEvent(this, eventToPublish));
                break;
            case LOG_SALE_AND_ADJUSTMENT:
                applicationEventPublisher.publishEvent(new LogSaleAndAdjustmentEvent(this, eventToPublish));
                break;
            default:
                break;

        }


    }

}
