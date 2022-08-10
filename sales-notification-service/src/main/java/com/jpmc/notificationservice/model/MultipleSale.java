package com.jpmc.notificationservice.model;

public class MultipleSale extends Sale {


    private long productQuantity;

    public MultipleSale() {
        super();
    }

    public MultipleSale(String typeOfMessage, String productType, Double price, long productQuantity) {
        super(typeOfMessage, productType, price);
        this.productQuantity = productQuantity;
    }

    public MultipleSale(long productQuanity, Double price) {
        this.setPrice(price);
        this.setProductQuantity(productQuanity);
    }

    /**
     * @param productQuantity
     */
    public MultipleSale(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public long getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(long productQuantity) {
        this.productQuantity = productQuantity;
    }

    @Override
    public String toString() {
        return "MulitpleSale [productQuanity=" + productQuantity + "]";
    }


}
