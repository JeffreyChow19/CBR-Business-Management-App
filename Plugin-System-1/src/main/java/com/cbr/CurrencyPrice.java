package com.cbr;

import com.cbr.models.Pricing.BasePrice;
import com.cbr.models.Pricing.Price;
import com.cbr.models.Pricing.PriceDecorator;
import com.cbr.utils.AppSettings;


public class CurrencyPrice extends PriceDecorator {
    public CurrencyPrice(Price price){
        super(price);
    }
    @Override
    public Double getValue() {
        return super.getValue();
    }

    //    private
    public String toString(){
        return AppSettings.getInstance().getAdditionalSettings().get("currency") + " " + super.toString();
    }
}
