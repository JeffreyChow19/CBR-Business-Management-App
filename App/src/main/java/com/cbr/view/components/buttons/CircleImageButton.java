package com.cbr.view.components.buttons;

import com.cbr.view.theme.Theme;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class CircleImageButton extends Button {
    public CircleImageButton(double radius, String imageUrl, String backgroundColor) {
        super();

        // Set Button Shape and Size
        this.setShape(new Circle(radius));
        this.setMinSize(2 * radius, 2 * radius);
        this.setMaxSize(2 * radius, 2 * radius);
        this.setPickOnBounds(true);

        // Product Image
        Image image = new Image(imageUrl);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(radius);
        imageView.setFitHeight(radius);
        imageView.setPreserveRatio(true);

        // Add Image to StackPane
        StackPane stackPane = new StackPane(imageView);
        stackPane.setMouseTransparent(true);
        this.setGraphic(stackPane);

        // Set the background color to fill the button if the image has transparent area
        this.setStyle("-fx-background-color:" + backgroundColor + ";");

        this.setOnMouseEntered(event -> {
            this.setStyle("-fx-background-color:"+ Theme.darkenColor(backgroundColor, 0.8) + ";-fx-background-radius: 12;");
        });
        this.setOnMouseExited(event -> {
            this.setStyle("-fx-background-color:" + backgroundColor + ";");
        });

        // Set the Cursor to HAND
        this.setCursor(Cursor.HAND);
    }
}
