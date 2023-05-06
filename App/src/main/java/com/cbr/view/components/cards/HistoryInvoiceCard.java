package com.cbr.view.components.cards;

import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import com.cbr.view.theme.Theme;
import com.cbr.models.FixedInvoice;

public class HistoryInvoiceCard extends HBox {
    private FixedInvoice invoice;
    private Button checkInvoiceButton;

    public HistoryInvoiceCard(FixedInvoice invoice) {
        this.invoice = invoice;
        Label fixedInvoiceId = new Label(this.invoice.getId());
        fixedInvoiceId.setFont(Theme.getBodyFont());
        fixedInvoiceId.setTextFill(Color.WHITE);
        this.getChildren().addAll(fixedInvoiceId, this.checkInvoiceButton);
        this.setAlignment(Pos.CENTER);
        this.setMaxWidth(Theme.getScreenWidth() * 0.65);
        this.setHeight(Theme.getScreenWidth() * 0.3);
        this.setStyle("-fx-background-color:" + Theme.getPrimaryBase() + ";");
        this.setStyle("-fx-background-radius: 10;");
    }
}
