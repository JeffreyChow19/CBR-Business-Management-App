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
public class Customer extends Identifiable implements Serializable {
    protected String type;
    protected List<FixedInvoice> InvoiceList;
    public static Integer customerCount = 0;

    public Customer(){
        this.type = "customer";
        Customer.customerCount+=1;
        this.id = "C-"+ Customer.customerCount.toString();
    }

}


