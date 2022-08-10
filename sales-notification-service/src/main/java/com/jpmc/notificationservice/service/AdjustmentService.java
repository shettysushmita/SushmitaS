package com.jpmc.notificationservice.service;

import com.jpmc.notificationservice.dto.Adjustment;
import com.jpmc.notificationservice.dto.IndividualSale;
import com.jpmc.notificationservice.enums.AdjustmentType;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

@Service
public class AdjustmentService {

    public void calculateAdjustment(List<IndividualSale> saleList, Adjustment adjustment) {

        AdjustmentType operator = adjustment.getTypeOfAdjustment();
        List<IndividualSale> tempSaleList = new ArrayList<>();

        Comparator compareByVersion = Comparator.comparing(IndividualSale::getVersion);
        Map<Integer, Optional<IndividualSale>> mapToProcess = (Map<Integer, Optional<IndividualSale>>) saleList.stream()
                .collect(Collectors.groupingBy(IndividualSale::getId, Collectors.reducing(BinaryOperator.maxBy(compareByVersion))));

        for (Integer currId : mapToProcess.keySet()) {
            IndividualSale sale = mapToProcess.get(currId).get();

            int id = sale.getId();
            int version = sale.getVersion() + 1;
            double adjustmentPrice = sale.getQuantity() * adjustment.getAdjustmentPrice();

            switch (operator) {
                case ADD:
                    tempSaleList.add(new IndividualSale(sale.getProductType(), sale.getQuantity(), version,
                            sale.getPrice() + adjustmentPrice, id));
                    break;
                case MULTIPLY:
                    tempSaleList.add(new IndividualSale(sale.getProductType(), sale.getQuantity(), version,
                            sale.getPrice() * adjustment.getAdjustmentPrice(), id));
                    break;
                case SUBTRACT:
                    if (sale.getPrice() >= adjustmentPrice) {
                        tempSaleList.add(new IndividualSale(sale.getProductType(), sale.getQuantity(), version,
                                sale.getPrice() - adjustmentPrice, id));
                    }
                    break;
                default:
                    break;
            }

        }

        saleList.addAll(tempSaleList);
    }
}
