package com.jpmc.notificationservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmc.notificationservice.dao.ProductsDao;
import com.jpmc.notificationservice.dto.Product;
import com.jpmc.notificationservice.enums.ReportType;
import com.jpmc.notificationservice.model.Sale;
import com.jpmc.notificationservice.processor.IMessageProcessor;
import com.jpmc.notificationservice.processor.MessageProcessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Semaphore;

@Service
public class ProcessingService {

    @Autowired
    private LoggingService loggingService;

    @Autowired
    private ProductsDao productsDao;

    @Autowired
    private MessageProcessorFactory factory;

    @Autowired
    private ObjectMapper mapper;

    private static int processCount = 0;
    private final static int START_ADJUSTMENT_COUNT = 50;
    private final static int START_LOG_COUNT = 10;
    private final Semaphore canProcess = new Semaphore(START_ADJUSTMENT_COUNT);

    public void processMessage(Sale msg) {
        processCount++;
        processSale(msg);

        if (processCount % START_LOG_COUNT == 0) {
            loggingService.publishReport(productsDao.getAllProduct(), ReportType.LOG_SALE);
        }

        if (processCount % START_ADJUSTMENT_COUNT == 0) {
            System.out.println("Will start the adjustments for messages ,will not be accepting messages for sometime");
            loggingService.publishReport(productsDao.getAllProduct(), ReportType.LOG_SALE_AND_ADJUSTMENT);

            System.out.println("Adjustments done successfully ,Ready to read start Processing");
            canProcess.release(START_ADJUSTMENT_COUNT);
        }

    }


    private void processSale(Sale msg) {
        final Product product = new Product(msg.getProductType());

        if (!productsDao.isProductPresent(product)) {
            productsDao.saveProduct(product);
        }

        Product existingProduct = productsDao.getProduct(product);

        IMessageProcessor processor = factory.getProcessor(msg);
        processor.processMessage(existingProduct, msg);

    }

    public void add(Sale sale) {
        if (canProcess.tryAcquire()) {
            processMessage(sale);
        } else {
            System.out.println("Not accepting message right now , try later");
        }
    }
}
