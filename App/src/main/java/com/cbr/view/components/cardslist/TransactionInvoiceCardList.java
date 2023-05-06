package com.cbr.view.components.cardslist;

import com.cbr.models.InventoryProduct;
import com.cbr.models.Product;
import com.cbr.view.components.cards.TransactionInvoiceCard;
import com.cbr.view.pages.TransactionPage;
import com.cbr.view.theme.Theme;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import lombok.Getter;

import java.util.List;

public class TransactionInvoiceCardList extends VBox{
    private TransactionPage parent;
    private List<InventoryProduct> products;

    @Getter
    private VBox invoiceListContainer;

    private double width;

    private ScrollPane invoiceListPane;

    public TransactionInvoiceCardList(TransactionPage parent, double width, List<InventoryProduct> products) {
        this.parent = parent;
        this.width = width;
        this.products = products;
        this.setMinWidth(width);
        this.setPrefWidth(width);
        this.setMaxWidth(width);
        this.setStyle("-fx-background-color: " + Theme.getPrimaryDark());

        // LIST OF INVOICES
        invoiceListPane = new ScrollPane();
        invoiceListPane.setMinWidth(width);
        invoiceListPane.setPrefWidth(width);
        invoiceListPane.setMaxWidth(width);
        invoiceListPane.setBorder(null);
        invoiceListPane.setStyle("-fx-background-color:" + Theme.getPrimaryDark() + ";");
        invoiceListPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        invoiceListPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        invoiceListPane.setFitToHeight(true);

        invoiceListContainer = new VBox();
        invoiceListContainer.setMinWidth(width);
        invoiceListContainer.setPrefWidth(width);
        invoiceListContainer.setMaxWidth(width);
        invoiceListContainer.setStyle("-fx-background-color:" + Theme.getPrimaryDark() + ";");
        invoiceListContainer.setAlignment(Pos.TOP_CENTER);

        invoiceListPane.setContent(invoiceListContainer);

        VBox.setVgrow(invoiceListPane, Priority.ALWAYS);

        this.getChildren().addAll(invoiceListPane);
    }

    public void addInvoiceCard(Product product) {
        // Check if the product is already in the invoiceListContainer
        boolean productExists = false;
        for (Node node : invoiceListContainer.getChildren()) {
            if (node instanceof TransactionInvoiceCard) {
                TransactionInvoiceCard card = (TransactionInvoiceCard) node;
                if (card.getProduct().equals(product)) {
                    card.getNumberSpinner().setValue(parent.getTemporaryInvoice().getProductFrequencies().get(product.getId()));
                    card.getNumberSpinner().updateValueLabel();
                    productExists = true;
                    break;
                }
            }
        }

        // Only add a new card if the product is not already in the invoiceListContainer
        if (!productExists) {
            // Create a new TransactionInvoiceCard for the specified product
            TransactionInvoiceCard card = new TransactionInvoiceCard(parent, width, 50, product);
            // Add the card to the invoiceListContainer
            invoiceListContainer.getChildren().add(card);

            invoiceListPane.setVvalue(1.0);
        }
    }
    public void removeInvoiceCard(Product product) {
        // Iterate over the children of the invoiceListContainer
        for (Node node : invoiceListContainer.getChildren()) {
            // Check if the child is a TransactionInvoiceCard
            if (node instanceof TransactionInvoiceCard) {
                TransactionInvoiceCard card = (TransactionInvoiceCard) node;
                // Check if the product of the card matches the specified product
                if (card.getProduct().equals(product)) {
                    // Remove the card from the container
                    invoiceListContainer.getChildren().remove(card);
                    break;
                }
            }
        }
    }

}