package com.cbr.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
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
    protected Double point;

    public Member(){
        this.type = "member";
    }
    public Member(String id, List<String> invoiceList, String name, String phoneNumber){
        this.id = id;
        this.invoiceList = invoiceList;
        this.type = "member";
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.status = true;
        this.point = 0.0;
    }

    public void addPoint(Double point){
        this.point+=point;
    }

    public void subtractPoint(Integer point){
        this.point-=point;
    }
    public void buy(Product product){

    }


}


