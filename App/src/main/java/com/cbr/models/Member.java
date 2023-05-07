package com.cbr.models;

import com.cbr.models.Pricing.BasePrice;
import com.cbr.models.Pricing.Price;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Member extends Customer {
    @Getter
    protected String name;
    @Getter
    protected String phoneNumber;
    @Getter
    protected Boolean status;
    @Getter
    protected Price point;
    @Getter
    protected Map<String, String> additionalValue;

    public Member(){
        this.type = "member";
        this.additionalValue = new HashMap<>();
    }
    public Member(String id, List<String> invoiceList, String name, String phoneNumber, Map<String, String> additionalValue){
        this.id = id;
        this.invoiceList = invoiceList;
        this.type = "member";
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.status = true;
        this.point = new BasePrice(0.0);
        this.additionalValue = additionalValue;
    }

    public void addPoint(Double point){
        this.point = new BasePrice(this.point.getValue() + point);
    }

    public void buy(Product product){

    }


}


