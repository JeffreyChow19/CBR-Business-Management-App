package com.cbr.models;

import com.cbr.models.Pricing.BasePrice;
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

    public Double total() {
        return count * super.getSellPrice().getValue();
    }
}
