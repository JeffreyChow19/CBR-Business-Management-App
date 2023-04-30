package com.cbr.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class BoughtProduct extends Product {
    private Integer count;

    public BoughtProduct(InventoryProduct product, Integer count){
        super(product.getProductName(), product.getSellPrice());
        this.id = product.getId();
        this.count = count;
    }
}
