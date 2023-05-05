package com.cbr.view.components.labels;

import com.cbr.view.theme.Theme;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class DefaultLabel extends Label {
    public DefaultLabel(String text, String backgroundColor) {
        super();
        // Create text node
        Text textNode = new Text(text);
        textNode.setFont(Theme.getBodyFont());

        // Create background shape
        Rectangle background = new Rectangle();
        background.setArcWidth(4);
        background.setArcHeight(4);

        // Add text and background to stack pane
        this.getChildren().addAll(background, textNode);

        // Set padding
        this.setPadding(new Insets(4, 12, 4, 12));

        // Set size based on text
        double textWidth = textNode.getBoundsInLocal().getWidth();
        double textHeight = textNode.getBoundsInLocal().getHeight();
        this.setPrefSize(textWidth + 12 * 2, textHeight + 4 * 2);

        this.setStyle("-fx-background-color:" + backgroundColor);
    }
}
