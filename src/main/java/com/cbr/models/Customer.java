package com.cbr.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@AllArgsConstructor
@Getter
@Setter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Customer.class, name="customer"),
        @JsonSubTypes.Type(value = Member.class, name="member"),
        @JsonSubTypes.Type(value = VIP.class, name="VIP")
})
public class Customer implements Serializable {
    protected Integer id;
    protected List<Transaction> transactionList;
    public static Integer customerCount = 0;

    public Customer(){
        this.id = customerCount + 1;
        Customer.customerCount+=1;
    }
}


