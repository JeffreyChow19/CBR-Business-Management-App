package com.cbr.models;

import com.cbr.models.Pricing.BasePrice;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


public class InventoryProduct extends Product {
    @Getter
    @Setter
    private String imagePath;
    @Getter
    @Setter
    private Integer stock;
    @Getter
    @Setter
    private Boolean status;
    private static Integer productCount = 0;

    public InventoryProduct(){
        super();
        this.additionalValues = new HashMap<>();
    }
    public InventoryProduct(String productName, BasePrice sellPrice, BasePrice buyPrice, String imagePath, Integer stock, String category, Boolean status){
        super(productName, category, sellPrice, buyPrice);
        System.out.println(this.category);
        this.imagePath = imagePath;
        this.stock = stock;
        this.status = status;
        InventoryProduct.productCount++;
        this.id = "P-" + InventoryProduct.productCount.toString();
        this.additionalValues = new HashMap<>();
        System.out.println("from inventory product");
    }

}
