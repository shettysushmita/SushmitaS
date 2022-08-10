package com.jpmc.notificationservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmc.notificationservice.dao.ProductsDao;
import com.jpmc.notificationservice.dto.Product;
import com.jpmc.notificationservice.enums.AdjustmentType;
import com.jpmc.notificationservice.model.AdjustmentSale;
import com.jpmc.notificationservice.model.MultipleSale;
import com.jpmc.notificationservice.model.Sale;
import com.jpmc.notificationservice.processor.MessageProcessorFactory;
import com.jpmc.notificationservice.processor.MultipleSaleProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = {ProcessingService.class, ObjectMapper.class})
public class ProcessingServiceTest {

    @MockBean
    private LoggingService loggingService;

    @MockBean
    private ProductsDao productsDao;

    @MockBean
    private MessageProcessorFactory factory;

    @Autowired
    private ObjectMapper mapper;

    @InjectMocks
    private ProcessingService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(service, "mapper", mapper);
    }

    @Test
    public void when_processMessage_success() throws Exception {
        MultipleSaleProcessor processor = Mockito.mock(MultipleSaleProcessor.class);

        Mockito.when(productsDao.getProduct(any())).thenReturn(new Product("testProd"));
        Mockito.when(factory.getProcessor(any())).thenReturn(processor);
        Mockito.doNothing().when(processor).processMessage(any(), any());

        Sale sale = createSampleMessage();
        for (int i = 0; i < 10; i++) {
            service.add(sale);
        }
    }

    private Sale createSampleMessage() {
        return new Sale("Sale", "productType1", 10.00);
    }

    private MultipleSale createMultipleSampleMessage() {
        return new MultipleSale("MultipleSale", "productType2", 10.00, 10);
    }

    private AdjustmentSale createAddAdjustmentMessage() {
        return new AdjustmentSale("AdjustmentSale", "productType2", 10.00, AdjustmentType.ADD);
    }

    private AdjustmentSale createSubAdjustmentMessage() {
        return new AdjustmentSale("AdjustmentSale", "productType2", 10.00, AdjustmentType.SUBTRACT);
    }

    private AdjustmentSale createMulAdjustmentMessage() {
        return new AdjustmentSale("AdjustmentSale", "productType2", 10.00, AdjustmentType.MULTIPLY);
    }


}
