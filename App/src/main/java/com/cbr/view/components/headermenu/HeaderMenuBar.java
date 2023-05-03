package com.cbr.view.components.headermenu;

import com.cbr.view.components.tabmenu.TabMenuBar;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import lombok.Getter;

public class HeaderMenuBar extends MenuBar {
    @Getter
    private Menu navigationMenu;
    @Getter
    private Menu preferencesMenu;
    @Getter
    private TabMenuBar tabs;

    public HeaderMenuBar(TabMenuBar tabs) {
        super();
        this.tabs = tabs;
        /* Set Menu Items : Navigation Menu */
        this.navigationMenu = new Menu("Menu");

        /* Set Menu Items : Preferences Menu */
        this.preferencesMenu = new Menu("Preferences");

        /* Add to Self */
        this.getMenus().addAll(navigationMenu, preferencesMenu);

        // chore: link menu items to pages
    }

    public void addNewNavigationMenu(String menuName, Node menuContent){
        MenuItem newMenu = new MenuItem(menuName);
        newMenu.setOnAction(event -> tabs.addTab(menuName, menuContent));
        this.getNavigationMenu().getItems().add(newMenu);
    }

    public void addNewPreferencesMenu(String menuName, Node menuContent){
        MenuItem newMenu = new MenuItem(menuName);
        newMenu.setOnAction(event -> tabs.addTab(menuName, menuContent));
        this.getPreferencesMenu().getItems().add(newMenu);
    }

}
