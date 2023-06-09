package com.cbr;

import com.cbr.models.Pricing.BasePrice;
import com.cbr.models.TemporaryInvoice;
import com.cbr.view.components.cards.AdditionalCostCard;
import lombok.Setter;

public class TaxCard extends AdditionalCostCard {
    @Setter
    private TemporaryInvoice temporaryInvoice;
    public TaxCard(TemporaryInvoice temporaryInvoice, Double width, String label) {
        super (width, label);
        this.temporaryInvoice = temporaryInvoice;
    }
    @Override
    public void render(){
        this.getCardNumber().setText("+" + (new BasePrice(this.temporaryInvoice.total() * TemporaryInvoice.additionalCosts.get("Tax"))).toString());
    }
}
