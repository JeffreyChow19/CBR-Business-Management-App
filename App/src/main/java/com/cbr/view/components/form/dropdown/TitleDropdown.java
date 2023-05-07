package com.cbr.view.components.form.dropdown;

import com.cbr.view.theme.Theme;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import lombok.Getter;

import java.util.List;

public class TitleDropdown extends VBox {
    @Getter
    private Dropdown dropdown;
    public TitleDropdown(double width, String title, List<String> options) {
        this.setMinWidth(width);
        this.setPrefWidth(width);
        this.setMaxWidth(width);

        Label label = new Label(title);
        label.setFont(Theme.getHeading2Font());
        label.setStyle("-fx-text-fill: white;");

        dropdown = new Dropdown(options);

        this.getChildren().addAll(label, dropdown);
    }
}