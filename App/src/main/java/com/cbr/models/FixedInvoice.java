package com.cbr.models;

import com.cbr.App;
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
    private Double getPoint;

    public FixedInvoice(){
        FixedInvoice.invoiceCount += 1;
    }
    public FixedInvoice(List<BoughtProduct> products, String customerId, Double discount, Double usedPoint, Double getPoint){
        super(customerId);
        this.boughtProducts = products;
        FixedInvoice.invoiceCount += 1;
        this.id = "FI-" + FixedInvoice.invoiceCount.toString();
        this.discount = discount;
        this.usedPoint = usedPoint;
        this.getPoint = getPoint;
    }

    public void generateFixedInvoiceId() {
        if (App.getDataStore().getInvoices() != null){
            this.id = "FI-" + App.getDataStore().getInvoices().generateId();
        } else {
            this.id = "FI-1";
        }
    }

    public Double total() {
        double total = 0.0;
        for (BoughtProduct bp : this.boughtProducts){
            total += bp.total();
        }
        return total;
    }

    public void print(){
        // to implement
    }
}
