package com.cbr;

import com.cbr.plugin.Plugin;
import com.cbr.view.MainView;
import com.cbr.view.components.dropdown.Dropdown;
import com.cbr.view.theme.Theme;
import com.mifmif.common.regex.Main;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class CurrencyPlugin implements Plugin {
    public void load(){
        Label currencyLabel = new Label("Currency");
        currencyLabel.setFont(Theme.getBodyFont());
        currencyLabel.setTextFill(Color.WHITE);
//        Dropdown dropdown = new Dropdown();
//        MainView.getInstance().getSettingsPage().getFormContainer().getChildren().addAll(currencyLabel, dropdown);
    }
}
