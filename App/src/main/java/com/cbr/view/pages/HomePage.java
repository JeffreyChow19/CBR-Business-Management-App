package com.cbr.view.pages;

import com.cbr.view.components.buttons.PluginsButton;
import com.cbr.view.components.buttons.SettingsButton;
import com.cbr.view.components.buttons.TransparentButton;
import com.cbr.view.components.clockwidget.ClockWidget;
import com.cbr.view.components.header.tabmenu.TabMenuBar;
import com.cbr.view.components.labels.ToolTipLabel;
import com.cbr.view.components.logo.Logo;
import com.cbr.view.theme.Theme;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

        VBox left = new VBox();
        left.setMinWidth(Theme.getScreenWidth()*0.15);
        left.setPrefWidth(Theme.getScreenWidth()*0.15);
        left.setMaxWidth(Theme.getScreenWidth()*0.15);

        /* First Layer Setup (Clock, NIM, Three Navigation Button)*/
        Image logoIcon = new Image("file:assets/icons/capybucks_logo.png");
        ImageView logoContainer = new ImageView(logoIcon);
        logoContainer.setFitHeight(Theme.getScreenWidth() * 0.2);
        logoContainer.setFitWidth(Theme.getScreenWidth() * 0.2);
        HBox container = new HBox();
        VBox firstLayer = new VBox();
        ClockWidget clockWidget = new ClockWidget();
        clockWidget.setAlignment(Pos.CENTER);
        ToolTipLabel nim = new ToolTipLabel("COPYRIGHT 2023 - CBR","13516055 - Nathaniel Evan Gunawan\n" +
                "13521044 - Rachel Gabriela Chen\n" +
                "13521046 - Jeffrey Chow\n" +
                "13521074 - Eugene Yap Jin Quan\n" +
                "13521094 - Angela Livia Arumsari\n" +
                "13521100 - Alexander Jason");
        nim.setWrapText(true);
        nim.setFont(Theme.getBodyBoldFont());
        nim.setTextFill(Color.WHITE);
        nim.setPrefWidth(Theme.getScreenWidth()*0.15);

        HBox navButtonsContainer = new HBox();
        navButtonsContainer.setSpacing(10.0);
        navButtonsContainer.setAlignment(Pos.CENTER);

        clients = new TransparentButton("CLIENTS");
        transaction = new TransparentButton("TRANSACTIONS");
        inventoryManagement = new TransparentButton("INVENTORY MANAGEMENT");
        navButtonsContainer.getChildren().addAll(clients, transaction, inventoryManagement);
        navButtonsContainer.setSpacing(Theme.getScreenWidth() * 0.04);

        firstLayer.setAlignment(Pos.TOP_CENTER);
        firstLayer.setPadding(new Insets(0.07 * Theme.getScreenHeight(), 0, 0, 0));
        firstLayer.getChildren().addAll(logoContainer, clockWidget, navButtonsContainer, nim);
        firstLayer.setSpacing(Theme.getScreenHeight()*0.05);

        HBox.setHgrow(firstLayer, Priority.ALWAYS);
        container.setMinSize(Theme.getScreenWidth(), Theme.getScreenHeight());

        VBox prefButtonsContainer = new VBox();
        settings = new SettingsButton(25);
        plugins = new PluginsButton(25);
        prefButtonsContainer.getChildren().addAll(settings, plugins);
        prefButtonsContainer.setAlignment(Pos.TOP_RIGHT);
        prefButtonsContainer.setMinWidth(Theme.getScreenWidth()*0.15);
        prefButtonsContainer.setPrefWidth(Theme.getScreenWidth()*0.15);
        prefButtonsContainer.setMaxWidth(Theme.getScreenWidth()*0.15);

        firstLayer.setPickOnBounds(false);
        container.getChildren().addAll(left, firstLayer, prefButtonsContainer);
        container.setSpacing(0);
        this.getChildren().addAll(container);
    }

    public void setButtonActions(Node clientsTarget,
                                 Node transactionsTarget,
                                 Node inventoryTarget,
                                 Node settingsTarget,
                                 Node pluginsTarget) {
        this.clients.setOnAction(event -> TabMenuBar.getInstance().addTab("Clients", clientsTarget));
        this.transaction.setOnAction(event -> TabMenuBar.getInstance().addTab("Transaction", transactionsTarget));
        this.inventoryManagement.setOnAction(event -> TabMenuBar.getInstance().addTab("Inventory Management", inventoryTarget));
        this.settings.setOnAction(event -> TabMenuBar.getInstance().addTab("Settings", settingsTarget));
        this.plugins.setOnAction(event -> TabMenuBar.getInstance().addTab("Plugins", pluginsTarget));
    }
}
