package com.cbr.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@Getter
@Setter
public class VIP extends Member {
    static private Double discount = 0.1;
    private Double buyPrice;

    // needs to have all attributes, because it is from an already registered Customer/Member
    public VIP(Integer id, List<Transaction> transactionList, String name, String phoneNumber, Boolean status, Integer point, Double buyPrice){
        super(id, transactionList, name, phoneNumber);
        this.status = status;
        this.point = point;
        this.buyPrice = buyPrice;
    }
}
