package com.cbr.view.components.buttons;

import com.cbr.view.components.header.tabmenu.TabMenuBar;
import com.cbr.view.theme.Theme;
import javafx.scene.control.Label;

public class UpgradeButton extends DefaultButton {
    private String customerId;
    public UpgradeButton(String customerId){
        super(Theme.getScreenWidth()*0.08, Theme.getScreenHeight()*0.05,"Upgrade");
        this.customerId = customerId;
        this.setOnMouseClicked(event -> TabMenuBar.getInstance().addTab("Upgrade Customer#" + this.customerId, new Label("Upgrade Customer")));
        //chore: upgrade customer page
    }
}
