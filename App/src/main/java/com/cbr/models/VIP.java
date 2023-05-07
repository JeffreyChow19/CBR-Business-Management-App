package com.cbr.models;

import com.cbr.models.Pricing.Price;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class VIP extends Member {
    static private Double discount = 0.1;

    // needs to have all attributes, because it is from an already registered Customer/Member
    public VIP(){
        this.type = "VIP";
    }
    
    public VIP(String id, List<String> invoiceList, String name, String phoneNumber, Boolean status, Price point, Map<String, String> additionalValue){
        super(id, invoiceList, name, phoneNumber, additionalValue);
        this.type = "VIP";
        this.status = status;
        this.point = point;
    }
}
