package com.jpmc.notificationservice.processor;

import com.jpmc.notificationservice.dto.IndividualSale;
import com.jpmc.notificationservice.dto.Product;
import com.jpmc.notificationservice.model.Sale;
import org.springframework.stereotype.Component;

@Component
public class SingleSaleProcessor implements IMessageProcessor {

    @Override
    public void processMessage(Product product, Sale msg) {
        Sale ms = (Sale) msg;
        product.setTotalQuantity(product.getTotalQuantity() + 1);
        product.getListOfIndividualSales().add(new IndividualSale(ms.getProductType(), 1L, 0, ms.getPrice()));

    }

}
