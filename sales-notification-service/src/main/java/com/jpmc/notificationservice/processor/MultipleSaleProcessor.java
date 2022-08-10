package com.jpmc.notificationservice.processor;

import com.jpmc.notificationservice.dto.IndividualSale;
import com.jpmc.notificationservice.dto.Product;
import com.jpmc.notificationservice.model.MultipleSale;
import com.jpmc.notificationservice.model.Sale;
import org.springframework.stereotype.Component;

@Component
public class MultipleSaleProcessor implements IMessageProcessor {

    @Override
    public void processMessage(Product product, Sale msg) {
        MultipleSale ms = (MultipleSale) msg;
        product.setTotalQuantity(product.getTotalQuantity() + ms.getProductQuantity());
        product.getListOfIndividualSales().add(new IndividualSale(ms.getProductType(), ms.getProductQuantity(), 0, ms.getProductQuantity() * ms.getPrice()));

    }

}
