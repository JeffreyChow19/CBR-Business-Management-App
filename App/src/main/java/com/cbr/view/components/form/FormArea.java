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

import com.cbr.view.components.form.FormLabel;

public class FormArea extends BorderPane {
    @Getter
    private Label content;
    @Getter
    private BorderPane contentContainer;
    @Getter
    private TextField contentTextField;

    public FormArea(String _content, double width, double height) {
        // Text Area Container
        double contentContainerWidth = width;
        double contentContainerHeight = height;

        this.setMinSize(contentContainerWidth, contentContainerHeight);
        this.setPrefSize(contentContainerWidth, contentContainerHeight);
        this.setMaxSize(contentContainerWidth, contentContainerHeight);
        // Left Side
        content = new FormLabel(_content, contentContainerWidth, contentContainerHeight);

        // Right Side
        contentTextField = new TextField();
        contentTextField.setPromptText(_content);
        contentTextField.setPrefWidth(0.5 * contentContainerWidth);
        contentTextField.setPrefHeight(contentContainerHeight);

        this.setLeft(content);
        this.setRight(contentTextField);
        this.setPadding(new Insets(10));
    }

    public String getContentFromText() {
        return this.contentTextField.getText();
    }
}
