package com.cbr.view.components.buttons;

import com.cbr.view.theme.Theme;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import lombok.Getter;

@Getter
public class HistoryButton extends DefaultButton{
    private String customerId;
    public HistoryButton(String customerId){
        super(Theme.getScreenWidth()*0.08, Theme.getScreenHeight()*0.05,"History");
        this.customerId = customerId;
    }
}
