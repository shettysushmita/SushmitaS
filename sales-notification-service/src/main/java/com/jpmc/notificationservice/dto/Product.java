package com.jpmc.notificationservice.dto;

import java.util.*;
import java.util.function.BinaryOperator;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;

public class Product {

    private String productType;

    private long totalQuantity = 0L;

    private List<Adjustment> listOfAdjustment = new ArrayList<>();

    private List<IndividualSale> listOfIndividualSales = new ArrayList<>();


    public Product(String productType) {
        super();
        this.productType = productType;
    }

    public String getProductType() {
        return productType;
    }

    public long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public List<Adjustment> getListOfAdjustment() {
        return listOfAdjustment;
    }

    public Double getTotalCostProcessed() {
        Comparator<IndividualSale> compareByVersion = Comparator.comparing(IndividualSale::getVersion);
        Map<Integer, Optional<IndividualSale>> mapToProcess = (Map<Integer, Optional<IndividualSale>>) listOfIndividualSales.stream().collect(
                groupingBy(
                        IndividualSale::getId, reducing(BinaryOperator.maxBy(compareByVersion))));
        return mapToProcess.values().stream()
                .mapToDouble(individualSale -> individualSale.get().getPrice())
                .sum();

    }

    public List<IndividualSale> getListOfIndividualSales() {
        return listOfIndividualSales;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((productType == null) ? 0 : productType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (productType == null) {
            return other.productType == null;
        } else return productType.equals(other.productType);
    }

    @Override
    public String toString() {
        return "Product [productType=" + productType + ", totalQuantity=" + totalQuantity + ", listOfAdjustment="
                + listOfAdjustment + "]";
    }


}
