package com.cbr.models;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Hashtable;

@Getter
public class FixedInvoice extends TemporaryInvoice {
    private static Integer invoiceCount = 0;

    public FixedInvoice(String id, HashMap<String, Integer> productFrequencies, String customerId){
        FixedInvoice.invoiceCount += 1;
        this.id = "FI-" + FixedInvoice.invoiceCount.toString();
        this.productFrequencies = productFrequencies;
        this.createdAt = LocalDateTime.now();
        this.customerId = customerId;
    }

    public void print(){
        // to implement
    }
}
