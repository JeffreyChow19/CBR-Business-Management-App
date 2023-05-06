package com.cbr.view.components.headermenu;

import com.cbr.view.components.tabmenu.TabMenuBar;
import com.cbr.view.theme.Theme;
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

    private class HeaderMenu extends Menu {
        public HeaderMenu(String menuName) {
            super(menuName);
            this.getStyleClass().add("header-menu");
            this.setStyle(
                    "-fx-font-size: " + 24.0 / 1920 * Theme.getScreenWidth() +
                    ";-fx-font-family: Ubuntu" +
                    "; -fx-text-fill: white;"
            );

        }
    }

    public HeaderMenuBar(TabMenuBar tabs) {
        super();
        this.setStyle("-fx-background-color: " + Theme.getSecondaryBase() + ";-fx-text-fill: white;");
        this.tabs = tabs;
        /* Set Menu Items : Navigation Menu */
        this.navigationMenu = new HeaderMenu("Menu");

        /* Set Menu Items : Preferences Menu */
        this.preferencesMenu = new HeaderMenu("Preferences");

        /* Add to Self */
        this.getMenus().addAll(navigationMenu, preferencesMenu);

        // chore: link menu items to pages
    }

    public void addNewNavigationMenu(String menuName, Node menuContent){
        MenuItem newMenu = new MenuItem(menuName);
        newMenu.setStyle(
                "-fx-font-size: " + 24.0 / 1920 * Theme.getScreenWidth() +
                ";-fx-font-family: Ubuntu" +
                "; -fx-text-fill: white;"
        );
        newMenu.setOnAction(event -> tabs.addTab(menuName, menuContent));
        this.getNavigationMenu().getItems().add(newMenu);
    }

    public void addNewPreferencesMenu(String menuName, Node menuContent){
        MenuItem newMenu = new MenuItem(menuName);
        newMenu.setStyle(
                "-fx-font-size: " + 24.0 / 1920 * Theme.getScreenWidth() +
                ";-fx-font-family: Ubuntu" +
                "; -fx-text-fill: white;"
        );
        newMenu.setOnAction(event -> tabs.addTab(menuName, menuContent));
        this.getPreferencesMenu().getItems().add(newMenu);
    }

}
