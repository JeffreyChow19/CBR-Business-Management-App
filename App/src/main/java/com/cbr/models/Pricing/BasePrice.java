package com.cbr.models.Pricing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BasePrice implements Price {
    protected Double value;
    public BasePrice(Double value){
        this.value = value;
    }
    public String toString(){
        return String.format("%.2f", value);
    }
}
