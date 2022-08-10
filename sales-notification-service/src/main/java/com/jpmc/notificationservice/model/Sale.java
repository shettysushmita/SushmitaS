package com.jpmc.notificationservice.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "typeOfMessage")
@JsonSubTypes({
        @Type(value = MultipleSale.class, name = "MultipleSale"),
        @Type(value = AdjustmentSale.class, name = "AdjustmentSale")
})
/**we can add validation on parameter using spring-boot-starter-validation @Required**/
public class Sale {

    private String typeOfMessage;

    private String productType;

    private Double price;

    public Sale() {
    }

    public Sale(String typeOfMessage, String productType, Double price) {
        super();
        this.typeOfMessage = typeOfMessage;
        this.productType = productType;
        this.price = price;
    }


    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Sale [productType=" + productType + ", price=" + price + "]";
    }


}
