package com.cbr.view.components.buttons;

import com.cbr.view.theme.Theme;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import lombok.Getter;

@Getter
public class HistoryButton extends Button {
    private String customerId;
    public HistoryButton(String customerId){
        super("History");
        this.customerId = customerId;
        this.setTextFill(Color.WHITE);
        this.setFont(Theme.getBodyMediumFont());
        this.setPrefSize(Theme.getScreenWidth()*0.08, Theme.getScreenHeight()*0.05);
        this.setStyle("-fx-background-color:"+ Theme.getPrimaryLight()+"; -fx-background-radius: 8;");
    }
}
