package com.jpmc.notificationservice.processor;

import com.jpmc.notificationservice.dto.Product;
import com.jpmc.notificationservice.model.Sale;

public interface IMessageProcessor {

    public void processMessage(Product product, Sale msg);

}
