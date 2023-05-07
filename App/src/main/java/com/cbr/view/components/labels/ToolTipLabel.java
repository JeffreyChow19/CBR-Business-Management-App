package com.cbr.view.components.labels;

import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;

public class ToolTipLabel extends Label {
    public ToolTipLabel(String text) {
        super(text);

        // Create tooltip with full label text
        Tooltip tooltip = new Tooltip(text);

        // Show tooltip when hovering over label
        Tooltip.install(this, tooltip);
    }

    public ToolTipLabel(String title, String hover) {
        super(title);

        // Create tooltip with full label text
        Tooltip tooltip = new Tooltip(hover);

        // Show tooltip when hovering over label
        Tooltip.install(this, tooltip);
    }
}