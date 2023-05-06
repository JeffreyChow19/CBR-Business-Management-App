package com.cbr.view.components.buttons;

import com.cbr.view.theme.Theme;


public class SettingsButton extends CircleImageButton {
    public SettingsButton(double radius) {
        super(radius, "file:assets/icons/settings.png", Theme.getPrimaryLight());

        this.setStyle("-fx-background-color: transparent");

        this.setOnMouseEntered(event -> {
            this.setStyle("-fx-background-color: transparent");
        });
        this.setOnMouseExited(event -> {
            this.setStyle("-fx-background-color: transparent");
        });
    }
}

