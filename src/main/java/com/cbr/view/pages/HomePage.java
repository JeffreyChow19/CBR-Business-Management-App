package com.cbr.view.pages;

import com.cbr.view.components.clockwidget.ClockWidget;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class HomePage extends StackPane {
    private Button clients;
    private Button transaction;
    private Button inventoryManagement;
    private Button settings;
    private Button plugins;

    public HomePage() {
        super();

        /* First Layer Setup (Clock, NIM, Three Navigation Button)*/
        VBox firstLayer = new VBox();
        ClockWidget clockWidget = new ClockWidget();
        clockWidget.setAlignment(Pos.CENTER);

        Label nim = new Label("13516055 - Nathaniel Evan Gunawan\n" +
                "13521044 - Rachel Gabriela Chen\n" +
                "13521046 - Jeffrey Chow\n" +
                "13521074 - Eugene Yap Jin Quan\n" +
                "13521094 - Angela Livia Arumsari\n" +
                "13521100 - Alexander Jason");
        nim.setWrapText(true);

        HBox navButtonsContainer = new HBox();
        navButtonsContainer.setSpacing(10.0);
        navButtonsContainer.setAlignment(Pos.CENTER);
        clients = new Button("Clients");
        transaction = new Button("Transactions");
        inventoryManagement = new Button("Inventory Management");
        // chore: link buttons
        navButtonsContainer.getChildren().addAll(clients, transaction, inventoryManagement);

        firstLayer.setAlignment(Pos.CENTER);
        firstLayer.getChildren().addAll(clockWidget, nim, navButtonsContainer);


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
