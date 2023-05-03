package com.cbr.view.components.buttons;

import com.cbr.view.theme.Theme;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class UpgradeButton extends DefaultButton {
    private String customerId;
    public UpgradeButton(String customerId){
        super(Theme.getScreenWidth()*0.08, Theme.getScreenHeight()*0.05,"Upgrade");
        this.customerId = customerId;
    }
}
