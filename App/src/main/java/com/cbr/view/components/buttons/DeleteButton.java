package com.cbr.view.components.buttons;

import com.cbr.view.theme.Theme;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class DeleteButton extends Button {
    public DeleteButton(Double width, Double height, String buttonName){
        super(buttonName);
        this.setTextFill(Color.WHITE);
        this.setFont(Theme.getBodyMediumFont());
        this.setPrefSize(width, height);
        this.setCursor(Cursor.HAND);
        this.setStyle("-fx-background-color:"+ Theme.getAccentRed()+";-fx-background-radius: 12;");
        this.setOnMouseEntered(event -> {
            this.setStyle("-fx-background-color:"+ Theme.getDarkRed()+";-fx-background-radius: 12;");
        });
        this.setOnMouseExited(event -> {
            this.setStyle("-fx-background-color:"+ Theme.getAccentRed()+";-fx-background-radius: 12;");
        });
    }
}
