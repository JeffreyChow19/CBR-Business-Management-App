package com.cbr.view.components.cardslist;

import java.util.List;

import com.cbr.models.FixedInvoice;
import com.cbr.view.components.cards.HistoryInvoiceCard;
import com.cbr.view.theme.Theme;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class HistoryInvoiceCardList extends ScrollPane {
    private VBox container;
    private List<FixedInvoice> invoiceList;
    public HistoryInvoiceCardList(List<FixedInvoice> invoiceList) {
        this.container = new VBox();
        this.invoiceList = invoiceList;
        this.container.setAlignment(Pos.TOP_CENTER);
        this.container.setSpacing(30);
        this.container.setStyle("-fx-background-color:" + Theme.getPrimaryDark());
        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.setBorder(null);
        for (FixedInvoice i : this.invoiceList) {
            HistoryInvoiceCard c = new HistoryInvoiceCard(i);
            this.container.getChildren().add(c);
        }
        if (this.invoiceList.isEmpty()) {
            Label noHistoryLabel = new Label("Transaction history is empty.");
            noHistoryLabel.setFont(Theme.getBodyMediumFont());
            noHistoryLabel.setTextFill(Color.WHITE);
            this.container.getChildren().add(noHistoryLabel);
        }
        this.container.prefWidthProperty().bind(this.widthProperty());
        this.container.prefHeightProperty().bind(this.heightProperty());
        this.setContent(this.container);
        this.setStyle("-fx-background-color:transparent;");
    }
}
