package com.cbr.view.components.buttons;

import com.cbr.view.theme.Theme;
import javafx.scene.text.TextAlignment;

public class TransparentButton extends DefaultButton {
    public TransparentButton(String buttonName) {
        super(Theme.getScreenWidth()*0.15, Theme.getScreenHeight()*0.08, buttonName);
        this.setWrapText(true);
        this.setTextAlignment(TextAlignment.CENTER);
        this.setStyle("-fx-background-color: transparent;-fx-background-radius: 8; -fx-border-color: white; -fx-border-width: 2; -fx-border-radius: 8;");
        this.setOnMouseEntered(event -> {
            this.setStyle("-fx-background-color:"+ Theme.getSecondaryLight()+";-fx-background-radius: 8; -fx-border-color: white; -fx-border-width: 2; -fx-border-radius: 8;");
        });
        this.setOnMouseExited(event -> {
            this.setStyle("-fx-background-color: transparent;-fx-background-radius: 8; -fx-border-color: white; -fx-border-width: 2; -fx-border-radius: 8;");
        });

    }
}
