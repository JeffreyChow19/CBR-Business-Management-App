package com.cbr.models;

import com.cbr.App;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class TemporaryInvoice extends Invoice implements Serializable {
    protected Map<String, Integer> productFrequencies;  // <ProductId, Count>
    private static Integer invoiceCount = 0;
    public static Map<String, Double> additionalCosts = new HashMap<>(); // in percentage

    public TemporaryInvoice(String customerId){
        super(customerId);
        this.productFrequencies = new HashMap<String, Integer>();
        TemporaryInvoice.invoiceCount += 1;
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

    public Double getGrandTotal(){
        Double total = 0.0;
        for (Map.Entry<String, Integer> entry : productFrequencies.entrySet()){
            System.out.println(entry.getKey());
            Double basePrice = App.getDataStore().getInventory().getById(entry.getKey()).getSellPrice();
            System.out.println(basePrice);
            total+=(basePrice* entry.getValue());
            System.out.println(entry.getValue());
        }
        for (Double cost : additionalCosts.values()){
            total+=(cost*total);
            System.out.println(cost);
        }
        System.out.println(total);
        return total;
    }

    public static void addAdditionalCosts(String costName, Double cost){
        TemporaryInvoice.additionalCosts.put(costName, cost);
    }
}
