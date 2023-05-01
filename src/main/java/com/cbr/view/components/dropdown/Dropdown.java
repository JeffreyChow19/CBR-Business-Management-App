package com.cbr.view.components.dropdown;

import com.cbr.view.theme.Theme;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;

public class Dropdown extends ComboBox<String> {
    public Dropdown() {
        // Add options to the dropdown
        this.getItems().addAll("Option 1", "Option 2", "Option 3");

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
            System.out.println("Selected option: " + selectedOption);
        });
    }
}