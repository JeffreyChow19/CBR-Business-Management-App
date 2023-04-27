package com.cbr.view.components.buttons;

import com.cbr.view.theme.Theme;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class UpgradeButton extends Button {
    private String customerId;
    public UpgradeButton(String customerId){
        super("Upgrade");
        this.setFont(Theme.getBodyMediumFont());
        this.setTextFill(Color.WHITE);
        this.setPrefSize(Theme.getScreenWidth()*0.08, Theme.getScreenHeight()*0.05);
        this.setStyle("-fx-background-color:"+ Theme.getPrimaryLight()+"; -fx-background-radius: 8;");
    }
}
