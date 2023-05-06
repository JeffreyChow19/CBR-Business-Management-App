package com.cbr.models.Pricing;

import lombok.Getter;

public abstract class PriceDecorator implements Price{
    @Getter protected Price price;
    public PriceDecorator(Price price){
        this.price = price;
    }
    public String toString(){
        return price.toString();
    }
    public Double getValue(){
        return price.getValue();
    }
}
