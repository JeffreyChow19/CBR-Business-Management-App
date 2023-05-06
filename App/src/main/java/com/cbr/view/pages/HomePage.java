package com.cbr.view.pages;

import com.cbr.view.components.buttons.PluginsButton;
import com.cbr.view.components.buttons.SettingsButton;
import com.cbr.view.components.buttons.TransparentButton;
import com.cbr.view.components.clockwidget.ClockWidget;
import com.cbr.view.components.tabmenu.TabMenuBar;
import com.cbr.view.theme.Theme;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
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
        nim.setFont(Theme.getBodyMediumFont());
        nim.setTextFill(Color.WHITE);

        HBox navButtonsContainer = new HBox();
        navButtonsContainer.setSpacing(10.0);
        navButtonsContainer.setAlignment(Pos.CENTER);

        clients = new TransparentButton("CLIENTS");
        transaction = new TransparentButton("TRANSACTIONS");
        inventoryManagement = new TransparentButton("INVENTORY MANAGEMENT");
        navButtonsContainer.getChildren().addAll(clients, transaction, inventoryManagement);
        navButtonsContainer.setSpacing(Theme.getScreenWidth() * 0.04);

        firstLayer.setAlignment(Pos.CENTER);
        firstLayer.getChildren().addAll(clockWidget, nim, navButtonsContainer);
        firstLayer.setSpacing(Theme.getScreenHeight() * 0.05);


        /* Second Layer Setup: "Capybucks", Settings and Plugins icons  */
        BorderPane secondLayer = new BorderPane();

        HBox capybucksContainer = new HBox();
        Label capybucks = new Label("CAPYBUCKS");
        capybucks.setFont(Theme.getHeading2Font());
        capybucks.setStyle("-fx-text-fill: white");
        capybucksContainer.getChildren().add(capybucks);
        secondLayer.setLeft(capybucksContainer);

        VBox prefButtonsContainer = new VBox();
        settings = new SettingsButton(50);
        plugins = new PluginsButton(50);
        prefButtonsContainer.getChildren().addAll(settings, plugins);
        secondLayer.setRight(prefButtonsContainer);

        firstLayer.setPickOnBounds(false);
        secondLayer.setPickOnBounds(false);
        this.getChildren().addAll(firstLayer, secondLayer);

        // chore: fix styling
    }

    public void setButtonActions(TabMenuBar tabs,
                                 Node clientsTarget,
                                 Node transactionsTarget,
                                 Node inventoryTarget,
                                 Node settingsTarget,
                                 Node pluginsTarget) {
        this.clients.setOnAction(event -> tabs.addTab("Clients", clientsTarget));
        this.transaction.setOnAction(event -> tabs.addTab("Transaction", transactionsTarget));
        this.inventoryManagement.setOnAction(event -> tabs.addTab("Inventory Management", inventoryTarget));
        this.settings.setOnAction(event -> tabs.addTab("Settings", settingsTarget));
        this.plugins.setOnAction(event -> tabs.addTab("Plugins", pluginsTarget));
    }
}
