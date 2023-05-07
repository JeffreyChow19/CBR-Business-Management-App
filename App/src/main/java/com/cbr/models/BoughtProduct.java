package com.cbr.models;

import com.cbr.models.Pricing.BasePrice;
import com.cbr.models.Pricing.Price;

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
    public BoughtProduct(InventoryProduct product, Integer count, Map<String,String> additionalValues){
        super(product.getProductName(), product.getCategory(), new BasePrice(product.getSellPrice().getValue()), new BasePrice(product.getBuyPrice().getValue()), additionalValues);
        this.id = product.getId();
        this.count = count;
    }

    public BoughtProduct clone(){
        BoughtProduct newProduct = new BoughtProduct();
        newProduct.setSellPrice(new BasePrice( this.getSellPrice().getValue()));
        newProduct.setCategory(this.category);
        newProduct.setProductName(this.productName);
        newProduct.setId(this.id);
        newProduct.setAdditionalValues(this.additionalValues);
        newProduct.setBuyPrice(new BasePrice(this.getBuyPrice().getValue()));
        newProduct.setCount(this.count);
        return newProduct;
    }

    public Price total() {
        return new BasePrice(count * super.getSellPrice().getValue());
    }
}
