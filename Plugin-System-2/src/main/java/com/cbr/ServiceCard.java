package com.cbr;

import com.cbr.models.TemporaryInvoice;
import com.cbr.view.MainView;
import com.cbr.view.components.cards.AdditionalCostCard;
import lombok.Setter;

public class ServiceCard extends AdditionalCostCard {
    @Setter
    private TemporaryInvoice temporaryInvoice;
    public ServiceCard(TemporaryInvoice temporaryInvoice, Double width, String label) {
        super (width, label);
        this.temporaryInvoice = temporaryInvoice;
    }
    @Override
    public void render(){
        this.getCardNumber().setText("+"+String.format("%.2f", (this.temporaryInvoice.total() * TemporaryInvoice.additionalCosts.get("Service Charge"))));
    }

}