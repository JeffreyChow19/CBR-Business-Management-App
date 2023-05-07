package com.cbr.view.components.logo;

import com.cbr.view.components.labels.PageTitle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class Logo extends HBox {
    public Logo() {
        super();
        Image logoIcon = new Image("file:assets/icons/capybucks.png");
        ImageView logoContainer = new ImageView(logoIcon);
        logoContainer.setFitHeight(50);
        logoContainer.setFitWidth(50);
        PageTitle pageTitle = new PageTitle("CAPYBUCKS");
        this.getChildren().addAll(logoContainer, pageTitle);
        this.setPadding(new Insets(20));
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER_LEFT);
        this.setMaxHeight(80);
    }
}
