package com.cbr.view.components.form;

import com.cbr.view.theme.Theme;
import javafx.scene.Scene;
import javafx.scene.control.*;
import lombok.Getter;
import lombok.Setter;

import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;

public class FormLabel extends Label{

    public FormLabel(String _content, double contentContainerWidth, double contentContainerHeight) {
        super(_content);
        this.setFont(Theme.getHeading2Font());
        this.setTextFill(Color.WHITE);
        this.setAlignment(Pos.TOP_LEFT);
        this.setPrefWidth(0.5 * contentContainerWidth);
        this.setPrefHeight(contentContainerHeight);
    }
}
