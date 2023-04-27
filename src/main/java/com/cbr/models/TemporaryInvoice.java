package com.cbr.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Hashtable;
@NoArgsConstructor
@Getter
@Setter
public class Invoice extends Identifiable implements Serializable {
    private Hashtable<Integer, Integer> productFrequencies;  // <ProductId, Count>
    private Integer customerId;
    private LocalDateTime createdAt;
    private static Integer invoiceCount = 0;

    public Invoice(Integer customerId){
        this.customerId = customerId;
        Invoice.invoiceCount += 1;
        this.id = invoiceCount;
    }

    public void addProduct(Integer productId){
        if (productFrequencies.containsKey(productId)) {
            productFrequencies.put(productId, productFrequencies.get(productId) + 1);
        } else {
            productFrequencies.put(productId, 1);
        }
    }
}
