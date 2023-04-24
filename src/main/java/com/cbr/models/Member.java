package com.cbr.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
@NoArgsConstructor
@Getter
@Setter
public class Member extends Customer{
    protected String name;
    protected String phoneNumber;
    protected Boolean status;
    protected Integer point;

    public Member(Integer id, List<Transaction> transactionList, String name, String phoneNumber){
        super(id, transactionList);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.status = true;
        this.point = 0;
    }

}


