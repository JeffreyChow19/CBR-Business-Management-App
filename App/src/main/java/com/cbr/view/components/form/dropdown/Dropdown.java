package com.cbr.view.components.form.dropdown;

import com.cbr.view.theme.Theme;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Dropdown extends ComboBox<String> {
    @Getter @Setter
    private List<String> options;

    public Dropdown(List<String> options) {
        this.options = options;

        // Add options to the dropdown
        this.getItems().addAll(options);

        this.setStyle("-fx-font: " + Theme.getBodyMediumFont().getSize() + "px " + Theme.getBodyMediumFont().getFamily() + "; -fx-border-color: white; -fx-background-color: transparent; -fx-border-radius: 10; -fx-prompt-text-fill: white;");

        // Set the cell factory to create custom cells for the options
        this.setCellFactory(lv -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item);
                    setFont(Theme.getBodyMediumFont());
                    setStyle("-fx-background-color: "+ Theme.getPrimaryBase() + "; -fx-text-fill: white;");
                }
            }
        });

        // Set the prompt text
        this.setPromptText("Select an option");

        // Add an event handler for when an option is selected
        this.setOnAction(event -> {
            String selectedOption = this.getValue();
        });
    }
}