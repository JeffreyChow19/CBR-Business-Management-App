package com.cbr.view.components.dropdown;

import com.cbr.view.theme.Theme;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TitleDropdown extends VBox {
    public TitleDropdown(double width, double height, String title) {
        // Set the size
        this.setMinSize(width, height);
        this.setPrefSize(width, height);
        this.setMaxSize(width, height);

        Label label = new Label(title);
        label.setFont(Theme.getHeading2Font());
        label.setStyle("-fx-text-fill: white;");

        Dropdown dropdown = new Dropdown();

        this.getChildren().addAll(label, dropdown);
    }
}
