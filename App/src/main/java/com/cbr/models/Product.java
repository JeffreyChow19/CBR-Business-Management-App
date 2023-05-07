package com.cbr.models;

import com.cbr.models.Pricing.BasePrice;
import com.cbr.models.Pricing.Price;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public abstract class Product extends Identifiable implements Serializable {
    @Getter
    @Setter
    @NotNull
    protected String productName;
    @Getter
    @Setter
    @NotNull
    protected String category;
    @Getter
    @Setter
    @NotNull
    protected Price sellPrice;
    @Getter
    @Setter
    @NotNull
    protected Price buyPrice;
    @Getter @Setter
    protected Map<String, String> additionalValues;

    public Product(){
        this.additionalValues = new HashMap<>();
    }

    public Product(String productName, String category, BasePrice sellPrice, BasePrice buyPrice, Map<String, String> additionalValues){
        this.productName = productName;
        this.category = category;
        this.sellPrice = sellPrice;
        this.buyPrice = buyPrice;
        this.additionalValues = additionalValues;
    }
}
