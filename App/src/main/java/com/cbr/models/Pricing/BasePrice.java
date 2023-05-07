package com.cbr.models.Pricing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DecimalFormat;

@NoArgsConstructor
@Getter
@Setter
public class BasePrice implements Price {
    protected Double value;
    public BasePrice(Double value){
        this.value = value;
    }
    public String toString(){
        if (value == 0){
            return "0.00";
        }
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        return formatter.format(value);
    }
}
