package com.jpmc.notificationservice.model;

import com.jpmc.notificationservice.enums.AdjustmentType;

public class AdjustmentSale extends Sale {

    private AdjustmentType adjustmentType;

    public AdjustmentSale() {
        super();
    }

    public AdjustmentSale(String typeOfMessage, String productType, Double price, AdjustmentType adjustmentType) {
        super(typeOfMessage, productType, price);
        this.adjustmentType = adjustmentType;
    }

    public AdjustmentType getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(AdjustmentType adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    @Override
    public String toString() {
        return "AdjustmentSale [adjustmentType=" + adjustmentType + "]";
    }


}
