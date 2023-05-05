package com.cbr.view.components.spinner;

import com.cbr.models.Product;
import com.cbr.view.components.buttons.MinusButton;
import com.cbr.view.components.buttons.PlusButton;
import com.cbr.view.pages.TransactionPage;
import com.cbr.view.theme.Theme;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.Setter;

public class NumberSpinner extends HBox {
    @Getter
    @Setter
    private int value;
    private Label valueLabel;

    private TransactionPage parent;

    @Setter
    private Product product;

    public NumberSpinner(TransactionPage parent, int initialValue) {
        this.parent = parent;
        this.value = initialValue;

        Button decrementButton = new MinusButton(10);
        decrementButton.setOnAction(event -> decrementValue());

        valueLabel = new Label(Integer.toString(value));
        valueLabel.setFont(Theme.getBodyMediumFont());
        valueLabel.setStyle("-fx-text-fill: white");
        valueLabel.setMinWidth(20);
        valueLabel.setAlignment(Pos.CENTER);

        Button incrementButton = new PlusButton(10);
        incrementButton.setOnAction(event -> incrementValue());

        this.getChildren().addAll(decrementButton, valueLabel, incrementButton);
        this.setSpacing(8);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(5));
    }

    private void decrementValue() {
        value--;
        updateValueLabel();
        parent.removeProduct(product);
    }

    private void incrementValue() {
        parent.addProduct(product);
    }

    public void updateValueLabel() {
        valueLabel.setText(Integer.toString(value));
    }
}