package com.cbr.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

@Setter
@Getter
public class FixedInvoice extends Invoice {
    private List<BoughtProduct> boughtProducts;
    private Double discount;
    private Double usedPoint;
    private static Integer invoiceCount = 0;

    public FixedInvoice(){
        FixedInvoice.invoiceCount += 1;
    }
    public FixedInvoice(List<BoughtProduct> products, String customerId, Double discount, Double usedPoint){
        super(customerId);
        this.boughtProducts = products;
        FixedInvoice.invoiceCount += 1;
        this.id = "FI-" + FixedInvoice.invoiceCount.toString();
        this.discount = discount;
        this.usedPoint = usedPoint;
    }

    public void print(){
        // to implement
    }
}
