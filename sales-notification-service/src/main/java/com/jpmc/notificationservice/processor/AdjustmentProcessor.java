package com.jpmc.notificationservice.processor;

import com.jpmc.notificationservice.dto.Adjustment;
import com.jpmc.notificationservice.dto.Product;
import com.jpmc.notificationservice.model.AdjustmentSale;
import com.jpmc.notificationservice.model.Sale;
import org.springframework.stereotype.Component;

@Component
public class AdjustmentProcessor implements IMessageProcessor {

    @Override
    public void processMessage(Product p, Sale msg) {

        AdjustmentSale sale = (AdjustmentSale) msg;
        p.getListOfAdjustment().add(new Adjustment(sale.getProductType(), sale.getPrice(), sale.getAdjustmentType()));
    }

}
