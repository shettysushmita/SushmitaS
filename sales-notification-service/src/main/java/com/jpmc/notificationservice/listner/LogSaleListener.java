package com.jpmc.notificationservice.listner;

import com.jpmc.notificationservice.dto.Product;
import com.jpmc.notificationservice.listner.events.LogSalesEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Set;

@Component
public class LogSaleListener implements ApplicationListener<LogSalesEvent> {

    @Override
    public void onApplicationEvent(LogSalesEvent event) {
        Set<Product> set = (Set<Product>) event.getEntity();

        StringBuffer buffer = new StringBuffer(System.lineSeparator());
        buffer.append("****************************************SALE REPORT*********************************************");
        buffer.append(System.lineSeparator());

        set.stream()
                .sorted(Comparator.comparing(Product::getProductType))
                .forEach(i -> {
                    buffer.append(i.getProductType().toUpperCase() + "     Total Quantity Sold: "
                            + i.getTotalQuantity() + "     Total Cost: " + i.getTotalCostProcessed());
                    buffer.append(System.lineSeparator());
                });
        buffer.append("************************************************************************************************");
        buffer.append(System.lineSeparator());

        System.out.println(buffer);

    }


}
