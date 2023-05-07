package com.cbr.models;

import com.cbr.models.Pricing.BasePrice;
import com.cbr.models.Pricing.Price;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Member extends Customer {
    @Getter
    @NotNull
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

    @Override
    public Member clone(){
        Member newMember = new Member();
        newMember.setType(this.type);
        newMember.setId(this.id);
        newMember.setInvoiceList(this.invoiceList);
        newMember.setPoint(new BasePrice(this.getPoint().getValue()));
        newMember.setName(this.name);
        newMember.setAdditionalValue(this.additionalValue);
        newMember.setPhoneNumber(this.phoneNumber);
        newMember.setStatus(this.status);
        return newMember;
    }


}


