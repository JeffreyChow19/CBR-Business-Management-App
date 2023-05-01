package com.cbr.view.components.cards;

import com.cbr.view.components.spinner.NumberSpinner;
import com.cbr.view.theme.Theme;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class TransactionInvoiceCard extends HBox {
    public TransactionInvoiceCard(double width, double height, String name, double price) {
        this.setMinWidth(width);
        this.setPrefWidth(width);
        this.setMaxWidth(width);
        this.setAlignment(Pos.CENTER_LEFT);

        Label productName = new Label(name);
        productName.setFont(Theme.getBodyMediumFont());
        productName.setTextFill(Color.WHITE);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label pricePerItem = new Label("@" + String.format("%.0f", price));
        pricePerItem.setFont(Theme.getBodyMediumFont());
        pricePerItem.setTextFill(Color.WHITE);
        pricePerItem.setPadding(new Insets(0,20,0,20));

        NumberSpinner numberSpinner = new NumberSpinner(1);

        this.getChildren().addAll(productName, spacer, pricePerItem, numberSpinner);

        this.setStyle("-fx-background-color: " + Theme.darkenColor(Theme.getPrimaryDark(), 0.8));
    }
}
