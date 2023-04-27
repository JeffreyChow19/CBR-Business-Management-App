package com.cbr.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@Getter
@Setter
public class Member extends Customer{
    protected String name;
    protected String phoneNumber;
    protected Boolean status;
    protected Integer point;

    public Member(String id, List<FixedInvoice> invoiceList, String name, String phoneNumber){
        this.id = id;
        this.InvoiceList = invoiceList;
        this.type = "member";
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.status = true;
        this.point = 0;
    }

}


