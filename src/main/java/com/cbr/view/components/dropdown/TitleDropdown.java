package com.cbr.view.components.dropdown;

import com.cbr.view.theme.Theme;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TitleDropdown extends VBox {
    public TitleDropdown(double width, String title) {
        this.setMinWidth(width);
        this.setPrefWidth(width);
        this.setMaxWidth(width);

        Label label = new Label(title);
        label.setFont(Theme.getHeading2Font());
        label.setStyle("-fx-text-fill: white;");

        Dropdown dropdown = new Dropdown();

        this.getChildren().addAll(label, dropdown);
    }
}
