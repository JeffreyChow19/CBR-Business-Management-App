package com.cbr.view.components.cardslist;

import java.util.List;

import com.cbr.models.FixedInvoice;
import com.cbr.view.components.cards.HistoryInvoiceCard;
import com.cbr.view.theme.Theme;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class HistoryInvoiceCardList extends VBox {
    private List<FixedInvoice> invoiceList;
    public HistoryInvoiceCardList(List<FixedInvoice> invoiceList) {
        this.invoiceList = invoiceList;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        for (FixedInvoice i : this.invoiceList) {
            this.getChildren().add(new HistoryInvoiceCard(i));
        }
        if (this.invoiceList.isEmpty()) {
            Label noHistoryLabel = new Label("Transaction history is empty.");
            noHistoryLabel.setFont(Theme.getBodyMediumFont());
            noHistoryLabel.setTextFill(Color.WHITE);
            this.getChildren().add(noHistoryLabel);
        }
    }
}
