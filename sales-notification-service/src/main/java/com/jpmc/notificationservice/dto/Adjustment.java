package com.jpmc.notificationservice.dto;

import com.jpmc.notificationservice.enums.AdjustmentType;

public class Adjustment {

    private String productType;

    private Double adjustmentPrice;

    private AdjustmentType typeOfAdjustment;

    private boolean processed;

    public Adjustment(String productType, Double adjustmentPrice, AdjustmentType typeOfAdjustment) {
        super();
        this.productType = productType;
        this.adjustmentPrice = adjustmentPrice;
        this.typeOfAdjustment = typeOfAdjustment;
        this.processed = false;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public String getProductType() {
        return productType;
    }

    public Double getAdjustmentPrice() {
        return adjustmentPrice;
    }

    public AdjustmentType getTypeOfAdjustment() {
        return typeOfAdjustment;
    }

    @Override
    public String toString() {
        return "Adjustment [productType=" + productType + ", adjustmentPrice=" + adjustmentPrice + ", typeOfAdjustment="
                + typeOfAdjustment + "]";
    }


}
