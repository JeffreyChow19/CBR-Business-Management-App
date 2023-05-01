package com.cbr.view.components.cardslist;

import com.cbr.view.components.cards.TransactionInvoiceCard;
import com.cbr.view.theme.Theme;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class TransactionInvoiceCardList extends VBox{
    public TransactionInvoiceCardList(double width) {
        this.setMinWidth(width);
        this.setPrefWidth(width);
        this.setMaxWidth(width);
        this.setStyle("-fx-background-color: " + Theme.getPrimaryDark());
        this.setSpacing(30);

        // LIST OF INVOICES
        ScrollPane invoiceListPane = new ScrollPane();
        invoiceListPane.setMinWidth(width);
        invoiceListPane.setPrefWidth(width);
        invoiceListPane.setMaxWidth(width);
        invoiceListPane.setBorder(null);
        invoiceListPane.setStyle("-fx-background-color:" + Theme.getPrimaryDark() + ";");
        invoiceListPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        invoiceListPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        invoiceListPane.setFitToHeight(true);

        VBox invoiceListContainer = new VBox();
        invoiceListContainer.setMinWidth(width);
        invoiceListContainer.setPrefWidth(width);
        invoiceListContainer.setMaxWidth(width);
        invoiceListContainer.setStyle("-fx-background-color:" + Theme.getPrimaryDark() + ";");
        invoiceListContainer.setAlignment(Pos.TOP_CENTER);

        TransactionInvoiceCard transactionInvoiceCard = new TransactionInvoiceCard(width, 50, "Card", 50000);

        invoiceListContainer.getChildren().addAll(transactionInvoiceCard);

        invoiceListPane.setContent(invoiceListContainer);

        VBox.setVgrow(invoiceListPane, Priority.ALWAYS);

        // GRAND TOTAL
        HBox grandTotalContainer = new HBox();
        grandTotalContainer.setMinWidth(width);
        grandTotalContainer.setPrefWidth(width);
        grandTotalContainer.setMaxWidth(width);
        grandTotalContainer.setStyle("-fx-border-color: white");
        grandTotalContainer.setPadding(new Insets(15));

        Label grandTotal = new Label("Grand Total");
        grandTotal.setFont(Theme.getHeading2Font());
        grandTotal.setTextFill(Color.WHITE);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label totalPrice = new Label("50000");
        totalPrice.setFont(Theme.getHeading2Font());
        totalPrice.setTextFill(Color.WHITE);

        grandTotalContainer.getChildren().addAll(grandTotal, spacer, totalPrice);

        this.getChildren().addAll(invoiceListPane, grandTotalContainer);
    }
}
