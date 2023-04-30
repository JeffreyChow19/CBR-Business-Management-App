package com.cbr.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class VIP extends Member {
    static private Double discount = 0.1;
    private Double buyPrice;

    // needs to have all attributes, because it is from an already registered Customer/Member
    public VIP(){
        this.type = "VIP";
    }
    public VIP(String id, List<String> invoiceList, String name, String phoneNumber, Boolean status, Integer point, Double buyPrice){
        super(id, invoiceList, name, phoneNumber);
        this.type = "VIP";
        this.status = status;
        this.point = point;
        this.buyPrice = buyPrice;
    }


    public void buy(Product product){

    }

}
