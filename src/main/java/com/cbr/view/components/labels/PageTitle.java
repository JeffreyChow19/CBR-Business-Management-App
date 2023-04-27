package com.cbr.view.components.labels;

import com.cbr.view.theme.Theme;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;


public class PageTitle extends Label {
    public PageTitle(String title){
        this.setText(title);
        this.setFont(Theme.getHeading2Font());
        this.setTextFill(Color.WHITE);
    }
}
