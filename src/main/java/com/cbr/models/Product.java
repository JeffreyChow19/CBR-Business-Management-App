package com.cbr.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@NoArgsConstructor
@Getter
@Setter
public class Product extends Identifiable implements Serializable {
    private String productName;
    private Double buyPrice;
    private Double sellPrice;
    private Integer imagePath;
    private Integer stock;
    private String category;
    private Boolean status;
    private static Integer productCount = 0;

    public Product(String productName, Double buyPrice, Double sellPrice, Integer imagePath, Integer stock, String category, Boolean status){
        this.productName = productName;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.imagePath = imagePath;
        this.stock = stock;
        this.category = category;
        this.status = status;
        Product.productCount+=1;
        this.id = "P-" + Product.productCount.toString();
    }
}
