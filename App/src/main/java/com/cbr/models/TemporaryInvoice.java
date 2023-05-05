package com.cbr.models;

import com.cbr.App;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class TemporaryInvoice extends Invoice implements Serializable {
    protected Map<String, Integer> productFrequencies;  // <ProductId, Count>
    private static Integer invoiceCount = 0;
    public static Map<String, Double> additionalCosts = new HashMap<>(); // in percentage

    public TemporaryInvoice(){
        TemporaryInvoice.invoiceCount += 1;
    }
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

    public Double total() {
        Double total = 0.0;

        // SUM ALL PRODUCTS PRICE IN BILL
        for (Map.Entry<String, Integer> entry : productFrequencies.entrySet()){
            Double basePrice = App.getDataStore().getInventory().getById(entry.getKey()).getSellPrice();
            total += (basePrice * entry.getValue());
        }

        return total;
    }
    public Double grandTotal(){
        Double total = total();
        Double grandTotal = total();

        for (Double cost : additionalCosts.values()){
            grandTotal += (cost * total);
        }

        return grandTotal;
    }

    public static void addAdditionalCosts(String costName, Double cost){
        TemporaryInvoice.additionalCosts.put(costName, cost);
    }
}
