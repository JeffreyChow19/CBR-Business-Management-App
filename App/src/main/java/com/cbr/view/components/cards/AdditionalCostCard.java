package com.cbr.view.components.cards;

import com.cbr.view.theme.Theme;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

public class AdditionalCostCard extends HBox {
    @Getter
    Label cardNumber;
    public AdditionalCostCard(Double width, String label) {
        this.setMinWidth(width);
        this.setPrefWidth(width);
        this.setMaxWidth(width);
        this.setPadding(new Insets(5,15,5,15));

        Label cardLabel = new Label(label);
        cardLabel.setFont(Theme.getBodyMediumFont());
        cardLabel.setTextFill(Color.WHITE);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        cardNumber = new Label("0");
        cardNumber.setFont(Theme.getBodyMediumFont());
        cardNumber.setTextFill(Color.WHITE);

        this.getChildren().addAll(cardLabel, spacer, cardNumber);
    }
}
