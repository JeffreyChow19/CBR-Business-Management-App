package com.cbr.models;

public class BoughtProduct extends Product {
    private Integer count;

    public BoughtProduct(InventoryProduct product, Integer count){
        super(product.getProductName(), product.getSellPrice());
        this.id = product.getId();
        this.count = count;
    }
}
