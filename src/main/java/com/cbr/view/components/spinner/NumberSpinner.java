package com.cbr.view.components.spinner;

import com.cbr.view.components.buttons.MinusButton;
import com.cbr.view.components.buttons.PlusButton;
import com.cbr.view.theme.Theme;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import lombok.Getter;

public class NumberSpinner extends HBox {
    @Getter
    private int value;
    private Label valueLabel;

    public NumberSpinner(int initialValue) {
        this.value = initialValue;

        Button decrementButton = new MinusButton(10);
        decrementButton.setOnAction(event -> decrementValue());

        valueLabel = new Label(Integer.toString(value));
        valueLabel.setFont(Theme.getBodyMediumFont());
        valueLabel.setStyle("-fx-text-fill: white");
        double minWidth = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().computeStringWidth("00", valueLabel.getFont());
        valueLabel.setMinWidth(minWidth);
        valueLabel.setAlignment(Pos.CENTER);

        Button incrementButton = new PlusButton(10);
        incrementButton.setOnAction(event -> incrementValue());

        this.getChildren().addAll(decrementButton, valueLabel, incrementButton);
        this.setSpacing(8);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(5));
    }

    private void decrementValue() {
        if (value > 1) {
            value--;
            updateValueLabel();
        } else {
            // Remove this record
        }
    }

    private void incrementValue() {
        value++;
        updateValueLabel();
    }

    private void updateValueLabel() {
        valueLabel.setText(Integer.toString(value));
    }
}