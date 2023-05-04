package com.cbr.view.components.cards;

import com.cbr.models.Product;
import com.cbr.view.components.spinner.NumberSpinner;
import com.cbr.view.pages.TransactionPage;
import com.cbr.view.theme.Theme;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import lombok.Getter;

public class TransactionInvoiceCard extends HBox {
    private TransactionPage parent;
    @Getter
    private Product product;
    @Getter
    private NumberSpinner numberSpinner;
    public TransactionInvoiceCard(TransactionPage parent, double width, double height, Product product) {
        this.parent = parent;
        this.product = product;
        this.setMinWidth(width);
        this.setPrefWidth(width);
        this.setMaxWidth(width);
        this.setAlignment(Pos.CENTER_LEFT);

        Label productName = new Label(product.getProductName());
        productName.setFont(Theme.getBodyMediumFont());
        productName.setTextFill(Color.WHITE);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label pricePerItem = new Label("@" + String.format("%.2f", product.getSellPrice()));
        pricePerItem.setFont(Theme.getBodyMediumFont());
        pricePerItem.setTextFill(Color.WHITE);
        pricePerItem.setPadding(new Insets(0,20,0,20));

        numberSpinner = new NumberSpinner(parent, 1);
        numberSpinner.setProduct(product);

        this.getChildren().addAll(productName, spacer, pricePerItem, numberSpinner);

        this.setStyle("-fx-background-color: " + Theme.darkenColor(Theme.getPrimaryDark(), 0.8));
    }
}
