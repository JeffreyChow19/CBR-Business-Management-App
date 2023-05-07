package com.cbr.models;

import com.cbr.App;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
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
    @NotNull protected String type;
    @NotNull protected List<String> invoiceList; // list of Fixed Invoice ID
    public static Integer customerCount = 0;

    public Customer(){
        this.type = "customer";
        Customer.customerCount+=1;
        this.id = "CS-"+ Customer.customerCount.toString();
        this.invoiceList = new ArrayList<>();
    }

    public void generateCustomerId() {
        if (App.getDataStore().getClients() != null){
            this.id = "CS-" + App.getDataStore().getClients().generateId();
        } else {
            this.id = "CS-1";
        }
    }

    public Customer clone(){
        Customer newCustomer = new Customer();
        newCustomer.setType(this.type);
        newCustomer.setId(this.id);
        newCustomer.setInvoiceList(this.invoiceList);
        return newCustomer;
    }

}


