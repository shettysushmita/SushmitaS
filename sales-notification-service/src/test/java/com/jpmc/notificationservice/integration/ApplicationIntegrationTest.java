package com.jpmc.notificationservice.integration;

import com.jpmc.notificationservice.enums.AdjustmentType;
import com.jpmc.notificationservice.model.AdjustmentSale;
import com.jpmc.notificationservice.model.MultipleSale;
import com.jpmc.notificationservice.model.Sale;
import com.jpmc.notificationservice.service.ProcessingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationIntegrationTest {

   @Autowired
   private ProcessingService processingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Process Single Sales")
    void processSingleSale()  {
        Sale saleProduct1 = createSingleSale("Product1",10.00);
        for (int i = 0; i < 15; i++) {
            processingService.add(saleProduct1);
        }

        Sale saleProduct2 = createSingleSale("Product2",20.00);
        for (int i = 0; i < 15; i++) {
            processingService.add(saleProduct2);
        }

        Sale saleProduct3 = createSingleSale("Product3",30.00);
        for (int i = 0; i < 15; i++) {
            processingService.add(saleProduct3);
        }

        for (int i = 0; i < 15; i++) {
            processingService.add(saleProduct1);
        }
    }
    @Test
    @DisplayName("Process Single Sales with Adjustments")
    void processSingleSaleWithAdjustments() {
        Sale saleProduct1 = createSingleSale("Product1",10.00);
        for (int i = 0; i < 30; i++) {
            processingService.add(saleProduct1);
        }

        Sale saleProduct2 = createSingleSale("Product2",20.00);
        for (int i = 0; i < 19; i++) {
            processingService.add(saleProduct2);
        }

        processingService.add(createAddAdjustmentSale("Product1",10.00));

        Sale saleProduct3 = createSingleSale("Product3",30.00);
        for (int i = 0; i < 25; i++) {
            processingService.add(saleProduct3);
        }

        for (int i = 0; i < 24; i++) {
            processingService.add(saleProduct1);
        }
        processingService.add(createAddAdjustmentSale("Product1",10.00));

    }


    @Test
    @DisplayName("Process Multiple Sales")
    void processMultipleSale() {
        MultipleSale saleProduct1 = createMultipleSale("Product1",10.00);

        for (int i = 0; i < 15; i++) {
            processingService.add(saleProduct1);
        }

        MultipleSale saleProduct2 = createMultipleSale("Product2",20.00);
        for (int i = 0; i < 15; i++) {
            processingService.add(saleProduct2);
        }

        MultipleSale saleProduct3 = createMultipleSale("Product3",30.00);
        for (int i = 0; i < 10; i++) {
            processingService.add(saleProduct3);
        }

    }

    @Test
    @DisplayName("Process Sales of all the Types Single, Multiple, Adjustment")
    void processMixedSale() {
        Sale saleProduct1 = createSingleSale("Product1",10.00);
        for (int i = 0; i < 30; i++) {
            processingService.add(saleProduct1);
        }

        processingService.add(createAddAdjustmentSale("Product1",10.00));

        Sale saleProduct2 = createSingleSale("Product2",20.00);
        for (int i = 0; i < 18; i++) {
            processingService.add(saleProduct2);
        }

        processingService.add(createAddAdjustmentSale("Product2",10.00));

        MultipleSale multiSaleProduct1 = createMultipleSale("Product1",10.00);
        for (int i = 0; i < 30; i++) {
            processingService.add(multiSaleProduct1);
        }

        processingService.add(createAddAdjustmentSale("Product2",10.00));

        MultipleSale multiSaleProduct2 = createMultipleSale("Product2",20.00);
        for (int i = 0; i < 18; i++) {
            processingService.add(multiSaleProduct2);
        }

        processingService.add(createSubAdjustmentMessage("Product1",10.00));
    }

    @Test
    @DisplayName("Process Sales of all the Types Single, Multiple, Adjustment")
    void saleSimulator() {
        Sale saleProduct1 = createSingleSale("Apple",10.00);
        for (int i = 0; i < 30; i++) {
            processingService.add(saleProduct1);
        }

        Sale saleProduct2 = createSingleSale("Mango",10.00);
        for (int i = 0; i < 20; i++) {
            processingService.add(saleProduct2);
        }

       MultipleSale multiSaleProduct1 = createMultipleSale("Apple",5.00);
        for (int i = 0; i < 30; i++) {
            processingService.add(multiSaleProduct1);
        }

        MultipleSale multiSaleProduct2 = createMultipleSale("Mango",6.00);
        for (int i = 0; i < 20; i++) {
            processingService.add(multiSaleProduct2);
        }
        processingService.add(createAddAdjustmentSale("Apple",10.00));

        for (int i = 0; i < 49; i++) {
            processingService.add(saleProduct1);
        }
    }

    private Sale createSingleSale(String type, Double price) {
        return new Sale("Sale", type, price);
    }

    private MultipleSale createMultipleSale(String type, Double price) {
        return new MultipleSale("MultipleSale", type, price, 10);
    }

    private AdjustmentSale createAddAdjustmentSale(String type, Double price) {
        return new AdjustmentSale("AdjustmentSale", type, price, AdjustmentType.ADD);
    }

    private AdjustmentSale createSubAdjustmentMessage(String type, Double price) {
        return new AdjustmentSale("AdjustmentSale", type, price, AdjustmentType.SUBTRACT);
    }

    private AdjustmentSale createMulAdjustmentMessage(String type, Double price) {
        return new AdjustmentSale("AdjustmentSale", type, price, AdjustmentType.MULTIPLY);
    }


}
