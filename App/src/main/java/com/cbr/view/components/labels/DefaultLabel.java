package com.cbr.view.components.labels;

import com.cbr.view.theme.Theme;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DefaultLabel extends Label {
    public DefaultLabel(double width, String text, String backgroundColor) {
        super();
        // Set Label Shape and Size
        Rectangle background = new Rectangle();
        background.setArcWidth(4);
        background.setArcHeight(4);
        this.getChildren().add(background);
        this.setMinSize(width, 24);
        this.setPrefSize(width, 24);
        this.setPadding(new Insets(4));
        this.setPickOnBounds(true);

        this.setText(text);
        this.setFont(Theme.getCaptionFont());
        this.setTextFill(Color.WHITE);

        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color:" + backgroundColor + "; -fx-background-radius: 4;");
    }
}
