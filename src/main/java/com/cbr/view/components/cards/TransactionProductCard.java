package com.cbr.view.components.cards;

import com.cbr.models.Product;
import com.cbr.view.theme.Theme;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class TransactionProductCard extends VBox {

    public TransactionProductCard(Product product){
        this.setWidth(250);
        this.setHeight(100);

        System.out.println(this.getHeight());
        System.out.println(this.getWidth());

//        Image image = new Image("file:assets/images/empty.jpg");
//        ImageView imageView = new ImageView();
//        imageView.setImage(image);
//        imageView.setFitWidth(this.getWidth());
//        imageView.setFitHeight(0.85 * this.getHeight());
//
//        this.getChildren().add(imageView);

        Label label = new Label("hello");
        this.getChildren().add(label);

        this.setPadding(new Insets(20));
        this.setAlignment(Pos.CENTER);

        this.setStyle("-fx-background-color:" + Theme.getPrimaryBase() + ";  -fx-background-radius: 10;");
    }
}
