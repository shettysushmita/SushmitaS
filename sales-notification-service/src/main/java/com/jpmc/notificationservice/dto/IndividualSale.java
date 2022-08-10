package com.jpmc.notificationservice.dto;

public class IndividualSale {

    private static int sequence = 1;

    private String productType;

    private Long quantity;

    private int version;

    private Double price;

    private int id;


    public IndividualSale(String productType, Long quantity, int version, Double price) {
        super();
        this.productType = productType;
        this.quantity = quantity;
        this.version = version;
        this.price = price;
        this.id = sequence++;
    }


    public IndividualSale(String productType, Long quantity, int version, Double price, int id) {
        super();
        this.productType = productType;
        this.quantity = quantity;
        this.version = version;
        this.price = price;
        this.id = id;
    }


    public String getProductType() {
        return productType;
    }

    public Long getQuantity() {
        return quantity;
    }

    public int getVersion() {
        return version;
    }

    public Double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        return "IndividualSale [productType=" + productType + ", quantity=" + quantity + ", version=" + version
                + ", price=" + price + ", id=" + id + "]";
    }


}
