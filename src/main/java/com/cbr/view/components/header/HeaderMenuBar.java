package com.cbr.view.components.header;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class HeaderMenuBar extends MenuBar {
    public HeaderMenuBar() {
        super();

        /* Set Menu Items : Navigation Menu */
        Menu navigationMenu = new Menu("Menu");
        MenuItem navigationMenuItem1 = new MenuItem("Clients");
        MenuItem navigationMenuItem2 = new MenuItem("Inventory Management");
        MenuItem navigationMenuItem3 = new MenuItem("Export Statements");
        MenuItem navigationMenuItem4 = new MenuItem("Transaction");
        navigationMenu.getItems().addAll(navigationMenuItem1,
                navigationMenuItem2,
                navigationMenuItem3,
                navigationMenuItem4);

        /* Set Menu Items : Preferences Menu */
        Menu preferencesMenu = new Menu("Preferences");
        MenuItem preferencesMenuItem1 = new MenuItem("Settings");
        MenuItem preferencesMenuItem2 = new MenuItem("Plugins");
        preferencesMenu.getItems().addAll(preferencesMenuItem1, preferencesMenuItem2);

        /* Add to Self */
        this.getMenus().addAll(navigationMenu, preferencesMenu);

        // chore: link menu items to pages
    }
}
