package com.cbr.view.components.cards;

import com.cbr.models.Product;
import com.cbr.view.components.buttons.PlusButton;
import com.cbr.view.components.labels.ToolTipLabel;
import com.cbr.view.theme.Theme;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class TransactionProductCard extends StackPane {

    public TransactionProductCard(Product product){
        double width = 0.1 * Theme.getScreenWidth();
        double height = 0.2 * Theme.getScreenHeight();

        // Set Card Size
        this.setMinSize(width, height);
        this.setPrefSize(width, height);
        this.setMaxSize(width, height);

        // Product Image
        Image image = new Image(product.getImagePath());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(0.8 * height);
        Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        imageView.setClip(clip);
        this.getChildren().add(imageView);

        // Label and Add Button HBox
        HBox label_button = new HBox();
        label_button.setMinSize(width, 0.25 * height);
        label_button.setPrefSize(width, 0.25 * height);
        label_button.setMaxSize(width, 0.25 * height);
        label_button.setStyle("-fx-background-color:" + Theme.getSecondaryBase() + ";  -fx-background-radius: 0 0 10 10;");
        label_button.setAlignment(Pos.CENTER);
        StackPane.setAlignment(label_button, Pos.BOTTOM_CENTER);

        // Product Label
        Label productLabel = new ToolTipLabel(product.getProductName());
        productLabel.setFont(Theme.getHeading2Font());
        productLabel.setTextFill(Color.WHITE);

        // Add Region
        Region region = new Region();
        label_button.setHgrow(region, Priority.ALWAYS);

        // Add Product Button
        Button addButton = new PlusButton(0.07 * height);
        addButton.setOnMouseClicked(event -> {
            System.out.println(product.getProductName());
        });

        label_button.setPadding(new Insets(10));
        label_button.getChildren().addAll(productLabel, region, addButton);

        this.getChildren().add(label_button);

        this.setAlignment(Pos.TOP_CENTER);

        this.setStyle("-fx-background-color:" + Theme.getSecondaryBase() + ";  -fx-background-radius: 10;");
    }
}
