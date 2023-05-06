package com.cbr.models;

import com.cbr.App;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
    protected String type;
    protected List<String> invoiceList; // list of Fixed Invoice ID
    public static Integer customerCount = 0;

    public Customer(){
        this.type = "customer";
        Customer.customerCount+=1;
        this.id = "CS-"+ Customer.customerCount.toString();
        this.invoiceList = new ArrayList<>();
    }

    public void generateCustomerId() {
        System.out.println("heelo");
        if (App.getDataStore().getClients() != null){
            System.out.println("yiwww");
            this.id = "CS-" + App.getDataStore().getClients().generateId();
        } else {
            this.id = "CS-1";
        }
        System.out.println(this.id);
    }

    public void buy(Product product){

    }

}


