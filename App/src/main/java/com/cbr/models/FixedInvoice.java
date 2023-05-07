package com.cbr.models;

import com.cbr.App;
import com.cbr.models.Pricing.BasePrice;
import com.cbr.models.Pricing.Price;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Setter
@Getter
public class FixedInvoice extends Invoice {
    private List<BoughtProduct> boughtProducts;
    private Price discount;
    private Price usedPoint;
    private static Integer invoiceCount = 0;
    private Price getPoint;
    private Map<String, String> additionalCosts;
    private Price grandTotal;
    public FixedInvoice(){
        FixedInvoice.invoiceCount += 1;
        this.boughtProducts = new ArrayList<>();
        this.additionalCosts = new HashMap<>();
        this.discount = new BasePrice(0.0);
        this.usedPoint = new BasePrice(0.0);
        this.getPoint = new BasePrice(0.0);
    }
    public FixedInvoice(List<BoughtProduct> products, String customerId, Price discount, Price usedPoint, Price getPoint, Map<String, String> additionalCosts, Price grandTotal){
        super(customerId);
        this.boughtProducts = products;
        FixedInvoice.invoiceCount += 1;
        this.id = "FI-" + FixedInvoice.invoiceCount.toString();
        this.discount = discount;
        this.usedPoint = usedPoint;
        this.getPoint = getPoint;
        this.additionalCosts = additionalCosts;
        this.grandTotal = grandTotal;
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
            total += bp.total().getValue();
        }
        return total;
    }


    public Double getRevenue() {
        double modal = 0.0;
        for (BoughtProduct product: this.boughtProducts) {
            modal += (product.getCount() * product.getBuyPrice().getValue());
        }
        return grandTotal.getValue() - modal;
    }

    public FixedInvoice clone(){
        FixedInvoice newInvoice = new FixedInvoice();
        newInvoice.setCreatedAt(this.createdAt);
        newInvoice.setCustomerId(this.customerId);
        newInvoice.setGrandTotal(new BasePrice(this.getGrandTotal().getValue()));
        newInvoice.setDiscount(this.getDiscount());
        newInvoice.setAdditionalCosts(this.additionalCosts);
        newInvoice.setGetPoint(new BasePrice(this.getPoint.getValue()));
        newInvoice.setUsedPoint(this.usedPoint);
        newInvoice.setId(this.id);
        for (BoughtProduct product : this.boughtProducts){
            newInvoice.boughtProducts.add(product.clone());
        }
        return  newInvoice;
    }
}
