package com.cbr.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class InventoryProduct extends Product {
    private Double buyPrice;
    private String imagePath;
    private Integer stock;
    private String category;
    private Boolean status;
    private static Integer productCount = 0;

    public InventoryProduct(String productName, Double sellPrice, Double buyPrice, String imagePath, Integer stock, String category, Boolean status){
        super(productName, sellPrice);
        this.buyPrice = buyPrice;
        this.imagePath = imagePath;
        this.stock = stock;
        this.category = category;
        this.status = status;
        InventoryProduct.productCount++;
        this.id = "P-" + InventoryProduct.productCount.toString();
    }

}
