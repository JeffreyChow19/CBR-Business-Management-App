package com.cbr.view.components.cardslist;

import com.cbr.models.InventoryProduct;
import com.cbr.models.Product;
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

        Product product = new InventoryProduct();
        product.setProductName("hello");
        product.setSellPrice(2000.0);
        TransactionInvoiceCard transactionInvoiceCard = new TransactionInvoiceCard(width, 50, product);

        invoiceListContainer.getChildren().addAll(transactionInvoiceCard);

        invoiceListPane.setContent(invoiceListContainer);

        VBox.setVgrow(invoiceListPane, Priority.ALWAYS);

        this.getChildren().addAll(invoiceListPane);
    }

    public void update(){

    }

    public void addInvoiceCard() {

    }


}
