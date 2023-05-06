package com.cbr.view.components.buttons;

import com.cbr.view.theme.Theme;


public class PluginsButton extends CircleImageButton {
    public PluginsButton(double radius) {
        super(radius, "file:assets/icons/plugins.png", Theme.getPrimaryLight());

        this.setStyle("-fx-background-color: transparent");

        this.setOnMouseEntered(event -> {
            this.setStyle("-fx-background-color: transparent");
        });
        this.setOnMouseExited(event -> {
            this.setStyle("-fx-background-color: transparent");
        });
    }
}

