package com.cbr.view.components.labels;

import com.cbr.view.theme.Theme;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleLabel extends Label {
    public CircleLabel(double radius, String text, String backgroundColor) {
        super();
        // Set Button Shape and Size
        this.setShape(new Circle(radius));
        this.setMinSize(2 * radius, 2 * radius);
        this.setMaxSize(2 * radius, 2 * radius);
        this.setPickOnBounds(true);

        this.setText(text);
        this.setFont(Theme.getBodyMediumFont());
        this.setTextFill(Color.WHITE);

        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color:" + backgroundColor);
    }
}
