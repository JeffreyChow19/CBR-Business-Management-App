package com.cbr.view.pages;

import com.cbr.view.components.buttons.TransparentButton;
import com.cbr.view.components.clockwidget.ClockWidget;
import com.cbr.view.theme.Theme;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class HomePage extends StackPane {
    private Button clients;
    private Button transaction;
    private Button inventoryManagement;
    private Button settings;
    private Button plugins;

    public HomePage() {
        super();
        /* Theme */
        this.setStyle("-fx-background-color:" + Theme.getPrimaryDark());
        this.setMinSize(Theme.getScreenWidth(), Theme.getScreenHeight());

        /* First Layer Setup (Clock, NIM, Three Navigation Button)*/
        VBox firstLayer = new VBox();
        firstLayer.setMinSize(Theme.getScreenWidth(), Theme.getScreenHeight());
        ClockWidget clockWidget = new ClockWidget();
        clockWidget.setAlignment(Pos.CENTER);

        Label nim = new Label("13516055 - Nathaniel Evan Gunawan\n" +
                "13521044 - Rachel Gabriela Chen\n" +
                "13521046 - Jeffrey Chow\n" +
                "13521074 - Eugene Yap Jin Quan\n" +
                "13521094 - Angela Livia Arumsari\n" +
                "13521100 - Alexander Jason");
        nim.setWrapText(true);
        nim.setFont(Theme.getBodyFont());
        nim.setTextFill(Color.WHITE);

        HBox navButtonsContainer = new HBox();
        navButtonsContainer.setSpacing(10.0);
        navButtonsContainer.setAlignment(Pos.CENTER);

        clients = new TransparentButton("CLIENTS");
        transaction = new TransparentButton("TRANSACTIONS");
        inventoryManagement = new TransparentButton("INVENTORY MANAGEMENT");
        // chore: link buttons
        navButtonsContainer.getChildren().addAll(clients, transaction, inventoryManagement);
        navButtonsContainer.setSpacing(Theme.getScreenWidth() * 0.04);

        firstLayer.setAlignment(Pos.CENTER);
        firstLayer.getChildren().addAll(clockWidget, nim, navButtonsContainer);
        firstLayer.setSpacing(Theme.getScreenHeight() * 0.05);


        /* Second Layer Setup: "Capybucks", Settings and Plugins icons  */
        BorderPane secondLayer = new BorderPane();
        secondLayer.setLeft(new Label("Capybucks"));

        VBox prefButtonsContainer = new VBox();
        settings = new Button("Settings");
        plugins = new Button("Plugins");
        prefButtonsContainer.getChildren().addAll(settings, plugins);
        secondLayer.setRight(prefButtonsContainer);

        firstLayer.setPickOnBounds(false);
        secondLayer.setPickOnBounds(false);
        this.getChildren().addAll(firstLayer, secondLayer);

        // chore: fix styling
    }
}
