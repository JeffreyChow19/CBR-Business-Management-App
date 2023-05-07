package com.cbr;

import com.cbr.models.Pricing.Price;
import com.cbr.models.Pricing.PriceDecorator;


public class CurrencyPrice extends PriceDecorator {
    private String currencySymbol;
    public CurrencyPrice(Price price, String currencySymbol){
        super(price);
        this.currencySymbol = currencySymbol;
    }
    @Override
    public Double getValue() {
        return super.getValue();
    }

    //    private
    public String toString(){
        return this.currencySymbol + " " + super.toString();
    }
}
