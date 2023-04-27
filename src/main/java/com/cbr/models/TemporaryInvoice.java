package com.cbr.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
@NoArgsConstructor
@Getter
@Setter
public class TemporaryInvoice extends Invoice implements Serializable {
    private static Integer invoiceCount = 0;

    public TemporaryInvoice(String customerId){
        this.productFrequencies = new HashMap<String, Integer>();
        TemporaryInvoice.invoiceCount += 1;
        this.customerId = customerId;
        this.createdAt = LocalDateTime.now();
        this.id = "TI-" +invoiceCount.toString();
    }

    public void addProduct(String productId){
        if (productFrequencies.containsKey(productId)) {
            productFrequencies.put(productId, productFrequencies.get(productId) + 1);
        } else {
            productFrequencies.put(productId, 1);
        }
    }

    public void removeProduct(String productId) {
        Integer count = productFrequencies.getOrDefault(productId, 0);
        if (count > 0) {
            count--;
            if (count == 0) {
                productFrequencies.remove(productId);
            } else {
                productFrequencies.put(productId, count);
            }
        }
    }
}
