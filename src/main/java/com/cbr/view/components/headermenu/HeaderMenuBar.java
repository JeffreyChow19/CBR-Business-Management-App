package com.cbr.view.components.headermenu;

import com.cbr.view.components.tabmenu.TabMenuBar;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class HeaderMenuBar extends MenuBar {
    private final MenuItem clientsMenu;
    private final MenuItem inventoryManagementMenu;
    private final MenuItem exportStatementsMenu;
    private final MenuItem transactionMenu;
    private final MenuItem settingsMenu;
    private final MenuItem pluginsMenu;

    public HeaderMenuBar() {
        super();

        /* Set Menu Items : Navigation Menu */
        Menu navigationMenu = new Menu("Menu");
        clientsMenu = new MenuItem("Clients");
        inventoryManagementMenu = new MenuItem("Inventory Management");
        exportStatementsMenu = new MenuItem("Export Statements");
        transactionMenu = new MenuItem("Transaction");
        navigationMenu.getItems().addAll(clientsMenu,
                inventoryManagementMenu,
                exportStatementsMenu,
                transactionMenu);

        /* Set Menu Items : Preferences Menu */
        Menu preferencesMenu = new Menu("Preferences");
        settingsMenu = new MenuItem("Settings");
        pluginsMenu = new MenuItem("Plugins");
        preferencesMenu.getItems().addAll(settingsMenu, pluginsMenu);

        /* Add to Self */
        this.getMenus().addAll(navigationMenu, preferencesMenu);

        // chore: link menu items to pages
    }

    public void setOpenedTab(TabMenuBar targetTabMenu,
                             Node clientsPage,
                             Node inventoryPage,
                             Node exportStatementsPage,
                             Node transactionPage,
                             Node settingsPage,
                             Node pluginsPage) {
        clientsMenu.setOnAction(event -> targetTabMenu.addTab("Clients", clientsPage));
        inventoryManagementMenu.setOnAction(event -> targetTabMenu.addTab("Inventory Management", inventoryPage));
        exportStatementsMenu.setOnAction(event -> targetTabMenu.addTab("Export Statements", exportStatementsPage));
        transactionMenu.setOnAction(event -> targetTabMenu.addTab("Transactions", transactionPage));
        settingsMenu.setOnAction(event -> targetTabMenu.addTab("Settings", settingsPage));
        pluginsMenu.setOnAction(event -> targetTabMenu.addTab("Plugins", pluginsPage));
    }
}
