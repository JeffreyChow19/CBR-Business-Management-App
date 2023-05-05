package com.cbr.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public abstract class Product extends Identifiable implements Serializable {
    protected String productName;
    protected String category;
    protected Double sellPrice;
    protected Double buyPrice;

    public Product(String productName, String category, Double sellPrice, Double buyPrice){
        this.productName = productName;
        this.category = category;
        this.sellPrice = sellPrice;
        this.buyPrice = buyPrice;
    }

//    public abstract Double getGrandTotal();
}
