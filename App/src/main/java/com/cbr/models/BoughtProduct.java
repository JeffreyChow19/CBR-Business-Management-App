package com.cbr.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Setter
@Getter
public class BoughtProduct extends Product {
    private Integer count;
    public BoughtProduct(InventoryProduct product, Integer count){
        super(product.getProductName(), product.getCategory(), product.getSellPrice(), product.getBuyPrice());
        this.id = product.getId();
        this.count = count;
    }

}
