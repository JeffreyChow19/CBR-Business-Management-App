package com.cbr.models;

import com.cbr.App;
import com.cbr.models.Pricing.BasePrice;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;


public class InventoryProduct extends Product implements Cloneable{
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
        super(productName, category, sellPrice, buyPrice, new HashMap<>());
        this.imagePath = imagePath;
        this.stock = stock;
        this.status = status;
        InventoryProduct.productCount++;
        this.id = "IP-" + InventoryProduct.productCount.toString();
        this.additionalValues = new HashMap<>();
    }
    public InventoryProduct clone(){
        InventoryProduct newProduct = new InventoryProduct();
        newProduct.setSellPrice(new BasePrice( this.getSellPrice().getValue()));
        newProduct.setCategory(this.category);
        newProduct.setProductName(this.productName);
        newProduct.setStatus(this.status);
        newProduct.setImagePath(this.imagePath);
        newProduct.setStock(this.stock);
        newProduct.setId(this.id);
        newProduct.setAdditionalValues(this.additionalValues);
        newProduct.setBuyPrice(new BasePrice(this.getBuyPrice().getValue()));
        return newProduct;
    }

    public void generateInventoryProductId() {
        if (App.getDataStore().getInventory() != null){
            this.id = "IP-" + App.getDataStore().getInventory().generateId();
        } else {
            this.id = "IP-1";
        }
    }

}
