package com.jpmc.notificationservice.dao;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.jpmc.notificationservice.dto.Adjustment;
import com.jpmc.notificationservice.dto.Product;

@Service
public class ProductsDao {

    private final Set<Product> processedMessageSet = new HashSet<>();

    public void saveProduct(Product product) {

        if (!processedMessageSet.contains(product)) {
            processedMessageSet.add(product);
        }
    }

    public Product getProduct(Product product) {
        return processedMessageSet.stream()
                .filter(prod -> prod.equals(product))
                .findFirst().get();
    }

    public boolean isProductPresent(Product p) {
        return processedMessageSet.contains(p);
    }

    public Set<Product> getAllProduct() {
        return processedMessageSet;
    }



}
