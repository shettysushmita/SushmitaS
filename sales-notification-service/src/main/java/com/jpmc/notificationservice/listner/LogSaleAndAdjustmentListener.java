package com.jpmc.notificationservice.listner;

import com.jpmc.notificationservice.dto.Adjustment;
import com.jpmc.notificationservice.dto.Product;
import com.jpmc.notificationservice.listner.events.LogSaleAndAdjustmentEvent;
import com.jpmc.notificationservice.service.AdjustmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Component
public class LogSaleAndAdjustmentListener implements ApplicationListener<LogSaleAndAdjustmentEvent> {

    @Autowired
    private AdjustmentService service;

    @Override
    public void onApplicationEvent(LogSaleAndAdjustmentEvent event) {
        Set<Product> set = (Set<Product>) event.getEntity();

        StringBuffer buffer = new StringBuffer(System.lineSeparator());
        buffer.append("****************************************ADJUSTMENT REPORT***************************************");
        buffer.append(System.lineSeparator());
        buffer.append("************************************************************************************************");
        buffer.append(System.lineSeparator());

        set.stream()
                .sorted(Comparator.comparing(Product::getProductType))
                .forEach(i -> {
                    buffer.append(i.getProductType().toUpperCase() + " Adjustment Logs" + System.lineSeparator());
                    buffer.append("**************************************************" + System.lineSeparator());

                    List<Adjustment> adjustMent = new ArrayList<>();
                    i.getListOfAdjustment()
                            .stream()
                            .filter(adj -> adj.isProcessed() == false)
                            .sorted(Comparator.comparing(Adjustment::getProductType))
                            .forEach(a -> {
                                adjustMent.add(a);
                                buffer.append("Type of adjustment: " + a.getTypeOfAdjustment() + "   Adjustment price: " + a.getAdjustmentPrice());
                                buffer.append(System.lineSeparator());
                                buffer.append("Sale details before adjustment  ::::");
                                buffer.append("Total Quantity Sold: " + i.getTotalQuantity() + "     Total Cost: " + i.getTotalCostProcessed());
                                buffer.append("\n");
                                service.calculateAdjustment(i.getListOfIndividualSales(), a);
                                buffer.append("Sale details after adjustment   ::::");
                                buffer.append("Total Quantity Sold: " + i.getTotalQuantity() + "     Total Cost: " + i.getTotalCostProcessed());
                                buffer.append("\n");
                                a.setProcessed(true);
                            });


                    buffer.append("************************************************************************************************");
                    buffer.append(System.lineSeparator());
                    buffer.append("************************************************************************************************");
                    buffer.append(System.lineSeparator());
                });

        System.out.println(buffer);
    }
}
