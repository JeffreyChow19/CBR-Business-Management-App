package com.cbr.view.components.buttons;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class IconButton extends Button {

    public  IconButton(String imageUrl, double size) {
        super();
        // Product Image
        Image image = new Image(imageUrl);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
        imageView.setPreserveRatio(true);

        // Add Image to StackPane
        StackPane stackPane = new StackPane(imageView);
        stackPane.setMouseTransparent(true);
        this.setGraphic(stackPane);

        // Set the Cursor to HAND
        this.setCursor(Cursor.HAND);
    }
}
