package com.jpmc.notificationservice.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpmc.notificationservice.model.AdjustmentSale;
import com.jpmc.notificationservice.model.MultipleSale;
import com.jpmc.notificationservice.model.Sale;

@Service
public class MessageProcessorFactory {

    @Autowired
    private AdjustmentProcessor adjustmentProcessor;

    @Autowired
    private MultipleSaleProcessor multipleSaleProcessor;

    @Autowired
    private SingleSaleProcessor singleSaleProcessor;

    public IMessageProcessor getProcessor(Sale msg) {

        if (msg instanceof AdjustmentSale) {
            return adjustmentProcessor;
        } else if (msg instanceof MultipleSale) {
            return multipleSaleProcessor;
        } else {
            return singleSaleProcessor;
        }

    }

}
