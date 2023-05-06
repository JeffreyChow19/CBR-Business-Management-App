package com.cbr;

import com.cbr.models.TemporaryInvoice;
import com.cbr.view.MainView;
import com.cbr.view.components.cards.AdditionalCostCard;

public class TaxCard extends AdditionalCostCard {
    public TaxCard(Double width, String label) {
       super (width, label);
    }
    @Override
    public void render(){
        this.getCardNumber().setText("+"+(String.format("%.2f", MainView.getInstance().getTransactionPage().getTemporaryInvoice().total() * TemporaryInvoice.additionalCosts.get("Tax"))));
    }
}